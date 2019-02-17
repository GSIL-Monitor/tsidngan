package cn.dingan.tsdingan.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.dingan.tsdingan.contants.Contants;
import cn.dingan.tsdingan.dao.DaInsureMapper;
import cn.dingan.tsdingan.dao.DaOrderDetailMapper;
import cn.dingan.tsdingan.dao.DaOrderMapper;
import cn.dingan.tsdingan.dao.DriverSchoolMapper;
import cn.dingan.tsdingan.dao.SerialnoMapper;
import cn.dingan.tsdingan.model.AppntDTO;
import cn.dingan.tsdingan.model.BnfDTO;
import cn.dingan.tsdingan.model.BnfList;
import cn.dingan.tsdingan.model.DaInsure;
import cn.dingan.tsdingan.model.DaOrder;
import cn.dingan.tsdingan.model.DaOrderDetail;
import cn.dingan.tsdingan.model.DriverSchool;
import cn.dingan.tsdingan.model.InsuredDTO;
import cn.dingan.tsdingan.model.InsuredList;
import cn.dingan.tsdingan.model.MainContDTO;
import cn.dingan.tsdingan.model.MainContDTOList;
import cn.dingan.tsdingan.model.PolicyRequestDTO;
import cn.dingan.tsdingan.model.RemittGrpDTO;
import cn.dingan.tsdingan.model.Serialno;
import cn.dingan.tsdingan.model.TranslationDTO;
import cn.dingan.tsdingan.response.PolicyResponseDTO;
import cn.dingan.tsdingan.response.ResopnseMainContDTO;
import cn.dingan.tsdingan.service.InsureService;
import cn.dingan.tsdingan.service.OrderService;
import cn.dingan.tsdingan.utils.UserUtil;
import cn.dingan.tsdingan.utils.XMLUtil;
import cn.trasen.commons.util.ApplicationUtils;
import cn.trasen.core.entity.Result;
import tk.mybatis.mapper.entity.Example;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = true)
public class InsureServiceImpl implements InsureService{
    
	private Logger logger = LoggerFactory.getLogger(InsureServiceImpl.class);
    
    /**
     * 流水号
     */
    @Autowired
    private SerialnoMapper serialnoMapper;
    
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private DriverSchoolMapper driverSchoolMapper;
    
    @Autowired
    private DaInsureMapper  daInsureMapper; 
    
    
    @Autowired
    private DaOrderMapper daOrderMapper;
    
    @Autowired
    private DaOrderDetailMapper daOrderDetailMapper; 
    
    
    /**
     * 
    * @Title: insureTry
    * @Description: 投保试算接口
    * @param @param obj
    * @param @return    参数
    * @return Object    返回类型
    * @throws
    * @author jyq#trasen.cn
    * @date 2019年2月12日 下午5:13:20
     */
    @Transactional(readOnly=false)
    public Result insureTry(DaInsure record) {
    	Result result = new Result();
        try {
            //根据登录人查询机构信息
        	DriverSchool school = UserUtil.getUser();
            
            if(null!=school) {
                
                List<DaInsure> insuredArray = new ArrayList<>();
                //调用试算接口
                if(null==record.getInsuredArray() || record.getInsuredArray().size()==0) {
                    result.setMessage("投保人员为空,请添加投保人员");
                    result.setSuccess(false);
                    return result;
                }else {
                    //添加试算序号
                     for(int index = 0;index<insuredArray.size();index++) {
                         DaInsure daInsure = insuredArray.get(index);
                         daInsure.setSeqNo(index);
                         insuredArray.add(daInsure);
                     }
                     record.setInsuredArray(insuredArray);
                }
                
            	int nextNo  = getSerialno(school);
                
                String transSerialno = "01005"+nextNo+"8";
            	
                
                int flag = 0;
                
                
                String resultXML = underwritingRequest(insuredArray, school, transSerialno, Contants.trial);
                
                if(null!=resultXML) {
                    PolicyResponseDTO resultDto = XMLUtil.getResult(resultXML);
                    if(null!=resultDto) {
                        //获取返回接口列表
                        List<ResopnseMainContDTO> manContList = resultDto.getMainContDTOList();
                        
                        if(null!=manContList && manContList.size()>0) {
                            
                            for(ResopnseMainContDTO vo : manContList) {
                                
                                if(StringUtils.isNotBlank(vo.getFlag()) && "0".equals(vo.getFlag())) {
                                    
                                }else {
                                    
                                    flag ++;  
                                }
                            }
                        }
                    }else {
                    	result.setMessage("投保接口出错,请联系管理员!");
                    	result.setSuccess(false);
                    }
                }else {
                	result.setSuccess(false);
                    result.setMessage("投保接口出错,请联系管理员!");  
                }
                
                if(flag==0) {
                    
                    //试算成功 生成订单号
                    boolean isOrder = orderService.insertOrderByTry(record,school,transSerialno);
                    
                    if(isOrder) {
                        result.setMessage("成功");
                        result.setObject(transSerialno);
                        result.setSuccess(true);
                    }else {
                        result.setMessage("投保接口出错,请联系管理员!");
                        result.setObject(transSerialno);
                        result.setSuccess(false);
                    }
                    
                    
                }else {
                	result.setSuccess(false);
                    result.setMessage("投保接口出错,请联系管理员!");  
                }
            }else {
                result.setSuccess(false);
                result.setMessage("登录过期,请重新登录");  
            }
            
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.error(e.getMessage());
            result.setSuccess(false);
            result.setMessage("投保接口出错,请联系管理员!"); 
            
        }
        return result;
    }
    
    /**
     * 
    * @Title: underwritingRequest
    * @Description: 投保试算接口
    * @param @return    参数
    * @return String    返回类型
    * @throws
    * @author jyq#trasen.cn
    * @date 2019年2月18日 上午11:44:33
     */
    private String underwritingRequest(List<DaInsure> list,DriverSchool school,String transSerialno ,String interfaceType) {
        PolicyRequestDTO dto = setXML(list,school,transSerialno,interfaceType);
        String xml;
        try {
            xml = XMLUtil.convertToXml(dto,PolicyRequestDTO.class );
            xml = xml.replace("standalone=\"yes\"", "");
            //保险公司返回报文
            String resultXML = callInterface(xml,"underwritingRequest");
            
            System.out.println(resultXML);
            
            return resultXML;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return null;
    }
    
    
    /**
     * 完成支付回调
     */
    public String finishOrderByPay(DaOrder order,List<DaOrderDetail> detailList) {
        if(StringUtils.isNotBlank(order.getDriverSchoolId())) {
            DriverSchool school =  driverSchoolMapper.selectByPrimaryKey(order.getDriverSchoolId());
            
            //查询投保人员
            List<String> insureIds = new ArrayList<>();
            for(DaOrderDetail detail : detailList) {
                if(StringUtils.isNotBlank(detail.getInsureId())) {
                    insureIds.add(detail.getInsureId());
                }
            }
            if(insureIds.size()>0) {
                Example example = new Example(DaInsure.class);
                example.createCriteria().andEqualTo("isDeleted",cn.trasen.BootComm.Contants.IS_DELETED_FALSE).andEqualTo("driverSchoolId",order.getDriverSchoolId());
                example.and().andIn("insureId", insureIds);
                List<DaInsure> insureList = daInsureMapper.selectByExample(example);
                if(null!=insureList && insureList.size()>0) {
                    String resultXML = underwritingRequest(insureList, school, order.getCusorderid(), Contants.order);
                    
                    PolicyResponseDTO resultDto = XMLUtil.getResult(resultXML);
                    if(null!=resultDto) {
                        //获取返回接口列表
                        List<ResopnseMainContDTO> manContList = resultDto.getMainContDTOList();
                        //批次号
                        String transSerialno = resultDto.getPolicyRequestDTO().getTransSerialno();
                        
                        if(!transSerialno.equals(order.getCusorderid())) {
                            
                            //插入失败数据,手动承保
                            
                            return "订单号不一致,承保失败";
                        }
                        
                        if(null!=manContList && manContList.size()>0) {
                            for(ResopnseMainContDTO dto : manContList) {
                                if(StringUtils.isNotBlank(dto.getFlag()) && "0".equals(dto.getFlag())) {
                                    String contNo = dto.getContNo();//保单号
                                    String seqNo = dto.getMainContDTOSerialno();//投保序号
                                    
                                    //修改保单号
                                    for(DaOrderDetail orderDetail : detailList) {
                                        if(orderDetail.getSeqNo().equals(Integer.valueOf(seqNo))) {
                                            orderDetail.setContNo(contNo);
                                            orderDetail.setStatus("1");
                                            daOrderDetailMapper.updateByPrimaryKeySelective(orderDetail);
                                            
                                            //修改人员信息已投保
                                            for(DaInsure daInsure : insureList) {
                                                if(orderDetail.getInsureId().equals(daInsure.getInsureId())) {
                                                    daInsure.setIsInsure("2");
                                                    daInsureMapper.updateByPrimaryKeySelective(daInsure);
                                                }
                                            }
                                        }
                                    }
                                    
                                }
                            }
                            return "承保成功";
                        }
                    }
                }
            }
            
        }
        
        return null;
    }
    
    @Transactional(readOnly=false)
    public String insureTry() {
        try {
            
            //根据登录人查询机构信息
//        	DriverSchool school = UserUtil.getUser();
            
            
            
            DriverSchool school = new DriverSchool();
            school.setEmail("380053453@qq.com");
            school.setPhone("18911267760");
            school.setName("测试驾校");
            
        	List<DaInsure> list = new ArrayList<>();
            DaInsure vo1 = new DaInsure();
            vo1.setSex("M");
            vo1.setIdcard("43032119961127545X");
            vo1.setName("蒋亚球1");
            vo1.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("1996-11-27"));
            list.add(vo1);
            
            DaInsure vo2 = new DaInsure();
            vo2.setSex("N");
            vo2.setIdcard("211223198801151012");
            vo2.setName("娄鹏2");
            vo2.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("1988-10-16"));
            list.add(vo2);
            
            if(null!=school) {
                //调用试算接口
                
                int nextNo  = getSerialno(school);
                
                String transSerialno = "01005"+nextNo+"8";
                
                PolicyRequestDTO dto = setXML(list,school,transSerialno,"00");
                String xml = XMLUtil.convertToXml(dto,PolicyRequestDTO.class );
                
                xml = xml.replace("standalone=\"yes\"", "");
                System.out.println(xml);
                //保险公司返回报文
                String resultXML = callInterface(xml,"underwritingRequest");
                
                System.out.println("试算返回::::"+resultXML);
                
                if(null!=resultXML) {
                    PolicyResponseDTO resultDto = XMLUtil.getResult(resultXML);
                    if(null!=resultDto) {
                         
                        PolicyRequestDTO dto2 = setXML(list,school,transSerialno,"01");  
                        String xml2 = XMLUtil.convertToXml(dto2,PolicyRequestDTO.class );
                        
                        xml2 = xml2.replace("standalone=\"yes\"", "");
                        
                        System.out.println(xml2);
                        
                        String resultXML2 = callInterface(xml2,"underwritingRequest");
                        
                        System.out.println(resultXML2);
                        
                        PolicyResponseDTO resultDto2 = XMLUtil.getResult(resultXML2);
                        
                        if(null!=resultDto2 && null!=resultDto2.getMainContDTOList() && resultDto2.getMainContDTOList().size()>0) {
                            for(ResopnseMainContDTO resopnseMainContDTO : resultDto2.getMainContDTOList()) {
                                
                                if(StringUtils.isNotBlank(resopnseMainContDTO.getFlag()) && "0".equals(resopnseMainContDTO.getFlag())) {
                                    String contNo = resopnseMainContDTO.getContNo();
                                    System.out.println("吉祥保险返回保单号::========="+contNo);
                                }
                            }
                        }
                        
                        
                        return "成功";
                    }else {
                        return "投保接口出错,请联系管理员!";  
                    }
                }
            }
            
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            logger.error(e.getMessage());
            
        }
        
        return "投保接口出错,请联系管理员!";
    }
    
    
    /**
     * 
    * @Title: callInterface
    * @Description: webservice调用通用方法
    * @param @param xml
    * @param @param method
    * @param @return    参数
    * @return String    返回类型
    * @throws
    * @author jyq#trasen.cn
    * @date 2019年2月12日 下午5:33:21
     */
    private String callInterface(String xml,String method) {
        try {
            org.apache.axis.client.Service service = new org.apache.axis.client.Service();
            Call call = (Call)service.createCall();
            call.setTargetEndpointAddress(new java.net.URL(Contants.endpoint));
            //checkRequest
            //试算接口
            call.setOperationName(new  QName("http://outwardservice.lis.sinosoft.com",method ));

            String tResult = (String)call.invoke(new Object[] { xml });
            
            return tResult;
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    
    
    
    /**
     * 
    * @Title: getSerialno
    * @Description: 设置流水号
    * @param @return    参数
    * @return String    返回类型
    * @throws
    * @author jyq#trasen.cn
    * @date 2019年2月14日 下午5:01:47
     */
    private int getSerialno(DriverSchool school) {
        Example example = new Example(Serialno.class);
        example.createCriteria().andEqualTo("isDeleted",cn.trasen.BootComm.Contants.IS_DELETED_FALSE)
        .andEqualTo("type",1);
        List<Serialno> serialnoList = serialnoMapper.selectByExample(example);
        if(null!=serialnoList && serialnoList.size()>0) {
            Serialno record = serialnoList.get(0);
            int no = record.getSerialno();
            //计算流水号
            int nextNo = no+1;
            record.setSerialno(nextNo);
            serialnoMapper.updateByPrimaryKey(record);
            return nextNo;
        }else {
        	Serialno record = new Serialno();
        	int no = 10000001;
        	record.setId(ApplicationUtils.GUID32());
        	record.setSerialno(10000001);
        	record.setIsDeleted("N");
        	record.setType("1");
        	serialnoMapper.insert(record);
        	return no;
        }
        
    }
    
    /**
     * 
    * @Title: setXML
    * @Description: 试算接口格式化xml
    * @param @return    参数
    * @return PolicyRequestDTO    返回类型
    * @throws
    * @author jyq#trasen.cn
    * @date 2019年2月12日 下午5:15:59
     */
    private PolicyRequestDTO setXML(List<DaInsure> list,DriverSchool school,String transSerialno,String interfaceType) {
        
        
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sf1 = new SimpleDateFormat("HH:mm:ss");
        
        String today = sf.format(new Date());
        
        String time = sf1.format(new Date());
        
        PolicyRequestDTO dto = new PolicyRequestDTO();
        
        //保险公司信息
        TranslationDTO TranslationDTO = new TranslationDTO();
        TranslationDTO.setTransDate(today);
        TranslationDTO.setTransTime(time);
        TranslationDTO.setInterfaceType(interfaceType);//00试算  01 承保
        TranslationDTO.setChannel("01005-鼎安经纪保险-06");//渠道 怎么获取
        
        
        TranslationDTO.setTransSerialno(transSerialno);//流水号 怎么设置
        
        //保单主信息列表
        
        MainContDTOList MainContDTOList = new MainContDTOList();
        
        List<MainContDTO> dtoList = new ArrayList<>();
        for(DaInsure vo : list) {
            //保单主信息
            MainContDTO MainContDTO = new MainContDTO();
            MainContDTO.setPrem(new BigDecimal(10));
            
            //受益人列表
            BnfList BnfList = new BnfList();
            
            List<BnfDTO> BnfDTOS = new ArrayList<>();
            //受益人信息
            BnfDTO BnfDTO = new BnfDTO();
            BnfDTO.setBnfFlag("1");
            BnfDTOS.add(BnfDTO);
            BnfList.setBnfDTO(BnfDTOS);
            MainContDTO.setBnfList(BnfList);
            
            
            MainContDTO.setEndDate("2019-06-06");//终止日期 怎么获取
            MainContDTO.setEndTime("11:15:19");
            MainContDTO.setAgentCom("10005010101");//在售票系统中维护，以保险公司提供的为准 怎么获取
            MainContDTO.setContplanCode("S00031");//套餐编码
            
            //投保人信息
            AppntDTO AppntDTO = new AppntDTO();
            AppntDTO.setAppntSex("M".equals(vo.getSex())?"0":"1");
            AppntDTO.setAppntIdno(vo.getIdcard());
            AppntDTO.setAppntName(vo.getName());
            AppntDTO.setRelation1("00");
            AppntDTO.setRelation2("00");
            AppntDTO.setAppntBirth(sf.format(vo.getBirthDate()));
            AppntDTO.setAppntIdtype("0");
            AppntDTO.setAppntNativePlace("CHN");
            MainContDTO.setAppntDTO(AppntDTO);
            
            MainContDTO.setApplyDate(today);
            MainContDTO.setApplyTime(time);
            MainContDTO.setCvaliDate(today);//生效时间
            MainContDTO.setCvaliTime(time);
            MainContDTO.setManageCom("86430107");//管理机构 在售票系统中维护，以保险公司提供的为准 
            MainContDTO.setMainAmount(new BigDecimal(550420));//保额
            
            //被投保人信息列表
            InsuredList InsuredList = new InsuredList();
            List<InsuredDTO> InsuredDTOS = new ArrayList<>();
            
            //被投保人信息
            InsuredDTO InsuredDTO = new InsuredDTO();
            InsuredDTO.setMainFlag("1");
            InsuredDTO.setInsuredSex("M".equals(vo.getSex())?"0":"1");
            InsuredDTO.setInsuredIdno(vo.getIdcard());
            InsuredDTO.setInsuredName(vo.getName());
            InsuredDTO.setInsuredBirth(sf.format(vo.getBirthDate()));
            InsuredDTO.setInsuredIdtype("0");
            InsuredDTO.setRelationToAppnt("00");
            InsuredDTO.setInsuredNativePlace("CHN");
            
            InsuredDTOS.add(InsuredDTO);
            InsuredList.setInsuredDTO(InsuredDTOS);
            
            MainContDTO.setInsuredList(InsuredList);
            
            //汇缴单位信息
            RemittGrpDTO RemittGrpDTO = new RemittGrpDTO();
            RemittGrpDTO.setEMail(school.getEmail());
            RemittGrpDTO.setPhone(school.getPhone());
            RemittGrpDTO.setGrpName(school.getName());
            MainContDTO.setRemittGrpDTO(RemittGrpDTO);
            
            
            MainContDTO.setAdditionalAmount(new BigDecimal(0));
            
            MainContDTO.setMainContDTOSerialno(vo.getSeqNo()+"");//序号 怎么设置
            
            
            MainContDTO.setMainDevelop("inbankcode,S8600000|inbankaccno,43001780661059666666|inbankaccname, 建行长沙韭菜园支行（收）|paymode,L|tempfeeno, 1234567899");
            
            dtoList.add(MainContDTO);
            
        }
        
       
        MainContDTOList.setMainContDTO(dtoList);
        
        dto.setMainContDTOList(MainContDTOList);
        dto.setTranslationDTO(TranslationDTO);
        
        return dto;
    }
    
}
