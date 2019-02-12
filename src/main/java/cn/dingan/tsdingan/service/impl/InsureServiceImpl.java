package cn.dingan.tsdingan.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.dingan.tsdingan.contants.Contants;
import cn.dingan.tsdingan.dao.DriverSchoolMapper;
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
import cn.dingan.tsdingan.model.SysUser;
import cn.dingan.tsdingan.model.TranslationDTO;
import cn.dingan.tsdingan.service.InsureService;
import cn.dingan.tsdingan.utils.UserUtil;
import cn.dingan.tsdingan.utils.XMLUtil;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = true)
public class InsureServiceImpl implements InsureService{
    
    
    @Autowired
    private DriverSchoolMapper driverSchoolMapper;
    
    
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
    public String insureTry(List<DaInsure> list) {
        try {
            PolicyRequestDTO dto = setXML();
            String xml2 = XMLUtil.convertToXml(dto,PolicyRequestDTO.class );
            
            xml2 = xml2.replace("standalone=\"yes\"", "");
            
            SysUser user = UserUtil.getUser();
            
            
            //根据登录人查询机构信息
            if(StringUtils.isNotBlank(user.getDriverSchoolId())) {
                DriverSchool school = driverSchoolMapper.selectByPrimaryKey(user.getDriverSchoolId());
            }
            
            
            if(null!=list && list.size()>0) {
                
                for(DaInsure vo : list) {
                    
                }
                
            }
            
            

            
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
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
            call.setOperationName(new  QName(method,"underwritingRequest" ));

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
    * @Title: setXML
    * @Description: 试算接口格式化xml
    * @param @return    参数
    * @return PolicyRequestDTO    返回类型
    * @throws
    * @author jyq#trasen.cn
    * @date 2019年2月12日 下午5:15:59
     */
    private static PolicyRequestDTO setXML() {
        
        PolicyRequestDTO dto = new PolicyRequestDTO();
        
        
        //保险公司信息
        TranslationDTO TranslationDTO = new TranslationDTO();
        TranslationDTO.setTransDate("2018-06-06");
        TranslationDTO.setTransTime("11:15:19");
        TranslationDTO.setInterfaceType("00");//00试算  01 承保
        TranslationDTO.setChannel("01005-鼎安经纪保险-06");//渠道 怎么获取
        TranslationDTO.setTransSerialno("010050100008008");//流水号 怎么设置
        
        //保单主信息列表
        
        MainContDTOList MainContDTOList = new MainContDTOList();
        
        List<MainContDTO> dtoList = new ArrayList<>();
        
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
        MainContDTO.setContplanCode("S00031");
        
        //投保人信息
        AppntDTO AppntDTO = new AppntDTO();
        AppntDTO.setAppntSex("0");
        AppntDTO.setAppntIdno("430621198612062730");
        AppntDTO.setAppntName("贝多芬666");
        AppntDTO.setRelation1("00");
        AppntDTO.setRelation2("00");
        AppntDTO.setAppntBirth("1986-12-06");
        AppntDTO.setAppntIdtype("0");
        AppntDTO.setAppntNativePlace("CHN");
        
        MainContDTO.setAppntDTO(AppntDTO);
        
        MainContDTO.setApplyDate("2018-06-06");
        MainContDTO.setApplyTime("11:15:19");
        MainContDTO.setCvaliDate("2018-06-06");
        MainContDTO.setCvaliTime("11:15:19");
        MainContDTO.setManageCom("86430107");//管理机构 在售票系统中维护，以保险公司提供的为准 
        MainContDTO.setMainAmount(new BigDecimal(550420));//保额是从设置的
        
        //被投保人信息列表
        InsuredList InsuredList = new InsuredList();
        List<InsuredDTO> InsuredDTOS = new ArrayList<>();
        //被投保人信息
        InsuredDTO InsuredDTO = new InsuredDTO();
        InsuredDTO.setMainFlag("1");
        InsuredDTO.setInsuredSex("0");
        InsuredDTO.setInsuredIdno("430621198612062730");
        InsuredDTO.setInsuredName("贝多芬666");
        InsuredDTO.setInsuredBirth("1986-12-06");
        InsuredDTO.setInsuredIdtype("0");
        InsuredDTO.setRelationToAppnt("00");
        InsuredDTO.setInsuredNativePlace("CHN");
        
        InsuredDTOS.add(InsuredDTO);
        InsuredList.setInsuredDTO(InsuredDTOS);
        
        MainContDTO.setInsuredList(InsuredList);
        
        
        //汇缴单位信息
        RemittGrpDTO RemittGrpDTO = new RemittGrpDTO();
        RemittGrpDTO.setEMail("15925754@qq.com");
        RemittGrpDTO.setPhone("18620385582");
        RemittGrpDTO.setGrpName("测试驾培机构");
        MainContDTO.setRemittGrpDTO(RemittGrpDTO);
        
        
        MainContDTO.setAdditionalAmount(new BigDecimal(0));
        MainContDTO.setMainContDTOSerialno("1");//序号 怎么设置
        MainContDTO.setMainDevelop("inbankcode,S8600000|inbankaccno,43001780661059666666|inbankaccname, 建行长沙韭菜园支行（收）|paymode,L|tempfeeno, 1234567899");
        
        dtoList.add(MainContDTO);
        MainContDTOList.setMainContDTO(dtoList);
        
        dto.setMainContDTOList(MainContDTOList);
        dto.setTranslationDTO(TranslationDTO);
        
        return dto;
    }
    
}
