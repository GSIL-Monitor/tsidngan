package cn.dingan.tsdingan.utils;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import cn.dingan.tsdingan.model.AppntDTO;
import cn.dingan.tsdingan.model.BnfDTO;
import cn.dingan.tsdingan.model.BnfList;
import cn.dingan.tsdingan.model.InsuredDTO;
import cn.dingan.tsdingan.model.InsuredList;
import cn.dingan.tsdingan.model.MainContDTO;
import cn.dingan.tsdingan.model.MainContDTOList;
import cn.dingan.tsdingan.model.PolicyRequestDTO;
import cn.dingan.tsdingan.model.RemittGrpDTO;
import cn.dingan.tsdingan.model.TranslationDTO; 

public class CxfClient {
    
//    final static String xml =
//
//            "<?xml version=\"1.0\" encoding=\"GBK\"?>\r\n" + 
//            "<PolicyRequestDTO> \r\n" + 
//            "  <TranslationDTO> \r\n" + 
//            "    <TransDate>2018-10-10</TransDate>  \r\n" + 
//            "    <TransTime>11:45:05</TransTime>  \r\n" + 
//            "    <InterfaceType>01</InterfaceType>  \r\n" + 
//            "    <Channel>01005-鼎安经纪保险-06</Channel>  \r\n" + 
//            "    <TransSerialno>01005100000130088</TransSerialno> \r\n" + 
//            "  </TranslationDTO>  \r\n" + 
//            "  <MainContDTOList> \r\n" + 
//            "    <MainContDTO> \r\n" + 
//            "      <ApplyDate>2018-10-10</ApplyDate>  \r\n" + 
//            "      <ApplyTime>11:45:05</ApplyTime>  \r\n" + 
//            "      <CvaliDate>2018-10-10</CvaliDate>  \r\n" + 
//            "      <CvaliTime>11:45:05</CvaliTime>  \r\n" + 
//            "      <EndDate>2019-10-10</EndDate>  \r\n" + 
//            "      <EndTime>11:45:05</EndTime>  \r\n" + 
//            "      <Prem>4.8</Prem>  \r\n" + 
//            "      <MainAmount>300000</MainAmount>  \r\n" + 
//            "      <AdditionalAmount/>  \r\n" + 
//            "      <ManageCom>86430107</ManageCom>  \r\n" + 
//            "      <AgentCom>10005010101</AgentCom>  \r\n" + 
//            "      <ContplanCode>S00018</ContplanCode>  \r\n" + 
//            "      <MainContDTOSerialno>1</MainContDTOSerialno> \r\n" + 
//            "      <MainDevelop>inbankcode,S8600000|inbankaccno,43001780661059666666|inbankaccname, 建行长沙韭菜园支行（收）|paymode,L|tempfeeno, 99199665588</MainDevelop>\r\n" + 
//            "    <RemittGrpDTO> \r\n" + 
//            "      <GrpName>宝鸡冶峰驾驶员培训学校</GrpName>  \r\n" + 
//            "      <Phone>18620385582</Phone>  \r\n" + 
//            "      <EMail>1403031090@qq.com</EMail> \r\n" + 
//            "      <TaxCode>91610304684787114C</TaxCode>\r\n" + 
//            "    </RemittGrpDTO> \r\n" + 
//            "    <AppntDTO> \r\n" + 
//            "      <Relation1>00</Relation1>  \r\n" + 
//            "      <Relation2>00</Relation2>  \r\n" + 
//            "      <AppntName>李四T0003</AppntName>  \r\n" + 
//            "      <AppntSex>0</AppntSex>  \r\n" + 
//            "      <AppntIdtype>0</AppntIdtype>  \r\n" + 
//            "      <AppntIdno>430682198607150018</AppntIdno>  \r\n" + 
//            "      <AppntBirth>1986-07-15</AppntBirth>  \r\n" + 
//            "      <AppntPhone>13786060639</AppntPhone>\r\n" + 
//            "      <AppntNativePlace>CHN</AppntNativePlace> \r\n" + 
//            "    </AppntDTO>  \r\n" + 
//            "    <InsuredList> \r\n" + 
//            "      <InsuredDTO> \r\n" + 
//            "        <InsuredName>李四T0003</InsuredName>  \r\n" + 
//            "        <InsuredSex>0</InsuredSex>  \r\n" + 
//            "        <InsuredSex>0</InsuredSex>  \r\n" + 
//            "        <InsuredIdtype>0</InsuredIdtype>  \r\n" + 
//            "        <InsuredIdno>430682198607150018</InsuredIdno>  \r\n" + 
//            "        <InsuredBirth>1986-07-15</InsuredBirth>  \r\n" + 
//            "        <MainFlag>1</MainFlag>  \r\n" + 
//            "         <InsuredPhone>13786060639</InsuredPhone>\r\n" + 
//            "        <RelationToAppnt>00</RelationToAppnt>  \r\n" + 
//            "        <InsuredNativePlace>CHN</InsuredNativePlace> \r\n" + 
//            "      </InsuredDTO> \r\n" + 
//            "    </InsuredList>  \r\n" + 
//            "    <BnfList> \r\n" + 
//            "      <BnfDTO> \r\n" + 
//            "        <BnfFlag>1</BnfFlag> \r\n" + 
//            "      </BnfDTO> \r\n" + 
//            "    </BnfList> \r\n" + 
//            "     </MainContDTO> \r\n" + 
//            "      <MainContDTO> \r\n" + 
//            "      <ApplyDate>2018-10-10</ApplyDate>  \r\n" + 
//            "      <ApplyTime>11:45:05</ApplyTime>  \r\n" + 
//            "      <CvaliDate>2018-10-10</CvaliDate>  \r\n" + 
//            "      <CvaliTime>11:45:05</CvaliTime>  \r\n" + 
//            "      <EndDate>2019-10-10</EndDate>  \r\n" + 
//            "      <EndTime>11:45:05</EndTime>  \r\n" + 
//            "      <Prem>50</Prem>  \r\n" + 
//            "      <MainAmount>320050</MainAmount>  \r\n" + 
//            "      <AdditionalAmount/>  \r\n" + 
//            "      <ManageCom>86430107</ManageCom>  \r\n" + 
//            "      <AgentCom>10005010101</AgentCom>  \r\n" + 
//            "      <ContplanCode>S00018</ContplanCode>  \r\n" + 
//            "      <MainContDTOSerialno>2</MainContDTOSerialno> \r\n" + 
//            "      <MainDevelop>inbankcode,S8600000|inbankaccno,43001780661059666666|inbankaccname, 建行长沙韭菜园支行（收）|paymode,L|tempfeeno, 99199665588</MainDevelop>\r\n" + 
//            "    <RemittGrpDTO> \r\n" + 
//            "      <GrpName>宝鸡冶峰驾驶员培训学校</GrpName>  \r\n" + 
//            "      <Phone>18620385582</Phone>  \r\n" + 
//            "      <EMail>1403031090@qq.com</EMail> \r\n" + 
//            "      <TaxCode>91610304684787114C</TaxCode>\r\n" + 
//            "    </RemittGrpDTO> \r\n" + 
//            "    <AppntDTO> \r\n" + 
//            "      <Relation1>00</Relation1>  \r\n" + 
//            "      <Relation2>00</Relation2>  \r\n" + 
//            "      <AppntName>李四T11</AppntName>  \r\n" + 
//            "      <AppntSex>0</AppntSex>  \r\n" + 
//            "      <AppntIdtype>0</AppntIdtype>  \r\n" + 
//            "      <AppntIdno>430682198607150018</AppntIdno>  \r\n" + 
//            "      <AppntBirth>1986-07-15</AppntBirth>  \r\n" + 
//            "      <AppntPhone>13786060639</AppntPhone>\r\n" + 
//            "      <AppntNativePlace>CHN</AppntNativePlace> \r\n" + 
//            "    </AppntDTO>  \r\n" + 
//            "    <InsuredList> \r\n" + 
//            "      <InsuredDTO> \r\n" + 
//            "        <InsuredName>李四T11</InsuredName>  \r\n" + 
//            "        <InsuredSex>0</InsuredSex>  \r\n" + 
//            "        <InsuredSex>0</InsuredSex>  \r\n" + 
//            "        <InsuredIdtype>0</InsuredIdtype>  \r\n" + 
//            "        <InsuredIdno>430682198607150018</InsuredIdno>  \r\n" + 
//            "        <InsuredBirth>1986-07-15</InsuredBirth>  \r\n" + 
//            "        <MainFlag>1</MainFlag>  \r\n" + 
//            "         <InsuredPhone>13786060639</InsuredPhone>\r\n" + 
//            "        <RelationToAppnt>00</RelationToAppnt>  \r\n" + 
//            "        <InsuredNativePlace>CHN</InsuredNativePlace> \r\n" + 
//            "      </InsuredDTO> \r\n" + 
//            "    </InsuredList>  \r\n" + 
//            "    <BnfList> \r\n" + 
//            "      <BnfDTO> \r\n" + 
//            "        <BnfFlag>1</BnfFlag> \r\n" + 
//            "      </BnfDTO> \r\n" + 
//            "    </BnfList> \r\n" + 
//            "     </MainContDTO> \r\n" + 
//            "  </MainContDTOList> \r\n" + 
//            "</PolicyRequestDTO>\r\n" + 
//            "";
	
	final static String xml ="<?xml version='1.0' encoding='utf-8'?><PolicyRequestDTO><TranslationDTO><Channel>01005-鼎安经纪保险-06</Channel><TransDate>2018-06-06</TransDate><TransTime>11:15:19</TransTime><InterfaceType>00</InterfaceType><TransSerialno>010050100008008</TransSerialno></TranslationDTO><MainContDTOList><MainContDTO><Prem>10</Prem><BnfList><BnfDTO><BnfFlag>1</BnfFlag></BnfDTO></BnfList><EndDate>2019-06-06</EndDate><EndTime>11:15:19</EndTime><AgentCom>10005010101</AgentCom><AppntDTO><AppntSex>0</AppntSex><AppntIdno>430621198612062730</AppntIdno><AppntName>贝多芬666</AppntName><Relation1>00</Relation1><Relation2>00</Relation2><AppntBirth>1986-12-06</AppntBirth><AppntIdtype>0</AppntIdtype><AppntNativePlace>CHN</AppntNativePlace></AppntDTO><ApplyDate>2018-06-06</ApplyDate><ApplyTime>11:15:19</ApplyTime><CvaliDate>2018-06-06</CvaliDate><CvaliTime>11:15:19</CvaliTime><ManageCom>86430107</ManageCom><MainAmount>550420</MainAmount><InsuredList><InsuredDTO><MainFlag>1</MainFlag><InsuredSex>0</InsuredSex><InsuredIdno>430621198612062730</InsuredIdno><InsuredName>贝多芬666</InsuredName><InsuredBirth>1986-12-06</InsuredBirth><InsuredIdtype>0</InsuredIdtype><RelationToAppnt>00</RelationToAppnt><InsuredNativePlace>CHN</InsuredNativePlace></InsuredDTO></InsuredList><ContplanCode>S00031</ContplanCode><RemittGrpDTO><EMail>15925754@qq.com</EMail><Phone>18620385582</Phone><GrpName>测试驾培机构</GrpName></RemittGrpDTO><AdditionalAmount/><MainContDTOSerialno>1</MainContDTOSerialno><MainDevelop>inbankcode,S8600000|inbankaccno,43001780661059666666|inbankaccname, 建行长沙韭菜园支行（收）|paymode,L|tempfeeno, 1234567899</MainDevelop></MainContDTO></MainContDTOList></PolicyRequestDTO>";

//    public static void main(String [] args) {
//        // 创建动态客户端  
//        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
//        Client client = dcf.createClient("https://tepos.jxlife.com.cn/ter/services/ThdpartyInterface?wsdl");
//        // 需要密码的情况需要加上用户名和密码
//        // client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME, PASS_WORD));
//        Object[] objects = new Object[0];
//        try {
//            objects = client.invoke("getThdpartyInterfaceAddress");
//            System.out.println("返回数据:" + objects[0]);
//        } catch (java.lang.Exception e) {
//            e.printStackTrace();
//        }
//    }
    
//    <?xml version=\"1.0\" encoding=\"GBK\"?>
//            <PolicyRequestDTO>
//                <TranslationDTO>
//                    <TransDate>2018-03-30</TransDate>
//                    <TransTime>12:25:08</TransTime>
//                    <TransSerialno></TransSerialno>
//                    <TransDevelop>01006-车站出单-03</TransDevelop>
//                </TranslationDTO>
//                <MainContDTO>
//                    <ApplyDate>2018-03-30</ApplyDate>
//                    <ApplyTime>12:25:08</ApplyTime>
//                    <CvaliDate>2018-03-30</CvaliDate>
//                    <CvaliTime>12:25:08</CvaliTime>
//                    <EndDate>2018-04-01</EndDate>
//                    <EndTime>12:25:08</EndTime>
//                    <CertifyNo></CertifyNo>
//                    <Prem>3</Prem>
//                    <MainAmount>68000</MainAmount>
//                    <AdditionalAmount>0</AdditionalAmount>
//                    <ManageCom>86430107</ManageCom>
//                    <AgentCom>10005010101</AgentCom>
//                    <ContplanCode>C00021</ContplanCode>
//                </MainContDTO>
//                <TrafficDTO>
//                    <TrainNo></TrainNo>
//                    <TicketNo>T45454523</TicketNo>
//                    <SeatNo>25</SeatNo>
//                    <SellDate>2018-03-30</SellDate>
//                    <SellTime>12:25:08</SellTime>
//                    <OperCode>SELL001</OperCode>
//                </TrafficDTO>
//                <AppntDTO>
//                    <Relation1>00</Relation1>
//                    <Relation2>00</Relation2>
//                    <AppntName>zzzp</AppntName>
//                    <AppntSex>0</AppntSex>
//                    <AppntIdtype>1</AppntIdtype>
//                    <AppntIdno>A457654343</AppntIdno>
//                    <AppntBirth>1992-06-18</AppntBirth>
//                    <AppntDevelop></AppntDevelop>
//                </AppntDTO>
//                <InsuredList>
//                    <InsuredDTO>
//                        <InsuredName></InsuredName>
//                        <InsuredSex></InsuredSex>
//                        <InsuredIdtype></InsuredIdtype>
//                        <InsuredIdno></InsuredIdno>
//                        <InsuredBirth></InsuredBirth>
//                        <MainFlag></MainFlag>
//                        <RelationToMainInsured></RelationToMainInsured>
//                        <RelationToAppnt></RelationToAppnt>
//                    </InsuredDTO>
//                </InsuredList>
//                <BnfList>
//                    <BnfDTO>
//                        <BnfType></BnfType>
//                        <BnfFlag></BnfFlag>
//                        <BnfOrder></BnfOrder>
//                        <BnfName></BnfName>
//                        <BnfSex></BnfSex>
//                        <BnfIDType></BnfIDType>
//                        <BnfIDNo></BnfIDNo>
//                        <BnfBirthday></BnfBirthday>
//                        <BnfRelationToInsured></BnfRelationToInsured>
//                    </BnfDTO>
//                </BnfList>
//            </PolicyRequestDTO>
    

    /**
     * @param args
     */
    public static void main(String[] args) {
        try{
        	
        	PolicyRequestDTO dto = setXML();
        	
        	String xml2 = convertToXml(dto,PolicyRequestDTO.class );
        	
        	xml2 = xml2.replace("standalone=\"yes\"", "");
        	
        	System.out.println(xml2);
        	
        	
//        	System.out.println(xml);
//        	
            String endpoint = "https://tepos.jxlife.com.cn/ter/services/ThdpartyInterface?wsdl";

            
            Service service = new Service();
            Call call = (Call)service.createCall();
            call.setTargetEndpointAddress(new java.net.URL(endpoint));
            //checkRequest
            //试算接口
            call.setOperationName( new  QName( "http://outwardservice.lis.sinosoft.com","underwritingRequest" ));

            String tResult = (String)call.invoke(new Object[] { xml2 });
            
            System.out.println(tResult);
            
            
            
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        
    }
    
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
    
    public static String convertToXml(Object obj, Class<?> load) throws Exception {
    	  JAXBContext context = JAXBContext.newInstance(load);
          Marshaller marshaller = context.createMarshaller();
          marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
          marshaller.setProperty(Marshaller.JAXB_ENCODING, "GBK");
          StringWriter writer = new StringWriter();
          marshaller.marshal(obj,writer);
          return writer.toString();
 
    }

}
