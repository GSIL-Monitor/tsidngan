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
import cn.dingan.tsdingan.dao.SerialnoMapper;
import cn.dingan.tsdingan.model.AppntDTO;
import cn.dingan.tsdingan.model.BnfDTO;
import cn.dingan.tsdingan.model.BnfList;
import cn.dingan.tsdingan.model.DaInsure;
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
                //调用试算接口
            	
            	int nextNo  = getSerialno(school);
                
                String transSerialno = "01005"+nextNo+"8";
            	
                PolicyRequestDTO dto = setXML(record.getInsuredArray(),school,transSerialno);
                String xml = XMLUtil.convertToXml(dto,PolicyRequestDTO.class );
                
                xml = xml.replace("standalone=\"yes\"", "");
                //保险公司返回报文
                String resultXML = callInterface(xml,"underwritingRequest");
                
                System.out.println(resultXML);
                int flag = 0;
                
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
                    result.setMessage("成功");
                    result.setObject(transSerialno);
                    result.setSuccess(true);
                }else {
                	result.setSuccess(false);
                    result.setMessage("投保接口出错,请联系管理员!");  
                }
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
            vo1.setSex("0");
            vo1.setIdcard("430421199210208630");
            vo1.setName("蒋亚球");
            vo1.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("1992-10-20"));
            list.add(vo1);
//                
//                DaInsure vo2 = new DaInsure();
//                vo2.setSex("0");
//                vo2.setIdNumber("430421199210208630");
//                vo2.setName("蒋亚球");
//                vo2.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse("1992-10-20"));
//                list.add(vo2);
            
            if(null!=school) {
                //调用试算接口
                PolicyRequestDTO dto = setXML(list,school,"");
                String xml = XMLUtil.convertToXml(dto,PolicyRequestDTO.class );
                
                xml = xml.replace("standalone=\"yes\"", "");
                //保险公司返回报文
                String resultXML = callInterface(xml,"underwritingRequest");
                
                
                int flag = 0;
                
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
                        return "投保接口出错,请联系管理员!";  
                    }
                }else {
                    return "投保接口出错,请联系管理员!";  
                }
                
                if(flag==0) {
                    return "成功";
                }else {
                    return "投保信息有误,请修改或联系管理员!";
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
        .andEqualTo("driver_school_id",school.getDriverSchoolId());
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
        	record.setDriverSchoolId(school.getDriverSchoolId());
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
    private PolicyRequestDTO setXML(List<DaInsure> list,DriverSchool school,String transSerialno) {
        
        
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sf1 = new SimpleDateFormat("HH:mm:ss");
        
        
        String today = sf.format(new Date());
        
        String time = sf1.format(new Date());
        
        PolicyRequestDTO dto = new PolicyRequestDTO();
        
        //保险公司信息
        TranslationDTO TranslationDTO = new TranslationDTO();
        TranslationDTO.setTransDate(today);
        TranslationDTO.setTransTime(time);
        TranslationDTO.setInterfaceType("00");//00试算  01 承保
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
            MainContDTO.setCvaliDate("2018-06-06");//生效时间
            MainContDTO.setCvaliTime("11:15:19");
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
            MainContDTO.setMainContDTOSerialno("1");//序号 怎么设置
            MainContDTO.setMainDevelop("inbankcode,S8600000|inbankaccno,43001780661059666666|inbankaccname, 建行长沙韭菜园支行（收）|paymode,L|tempfeeno, 1234567899");
            
            dtoList.add(MainContDTO);
        }
        
       
        MainContDTOList.setMainContDTO(dtoList);
        
        dto.setMainContDTOList(MainContDTOList);
        dto.setTranslationDTO(TranslationDTO);
        
        return dto;
    }
    
}
