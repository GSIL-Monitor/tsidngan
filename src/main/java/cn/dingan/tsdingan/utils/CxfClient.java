package cn.dingan.tsdingan.utils;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import cn.dingan.tsdingan.model.AppntDTO;
import cn.dingan.tsdingan.model.InsuredDTO;
import cn.dingan.tsdingan.model.MainContDTO;
import cn.dingan.tsdingan.model.PolicyRequestDTO;
import cn.dingan.tsdingan.model.RemittGrpDTO;
import cn.dingan.tsdingan.model.TranslationDTO; 

public class CxfClient {
    
    final static String xml =

            "<?xml version=\"1.0\" encoding=\"GBK\"?>\r\n" + 
            "<PolicyRequestDTO> \r\n" + 
            "  <TranslationDTO> \r\n" + 
            "    <TransDate>2018-10-10</TransDate>  \r\n" + 
            "    <TransTime>11:45:05</TransTime>  \r\n" + 
            "    <InterfaceType>01</InterfaceType>  \r\n" + 
            "    <Channel>01005-鼎安经纪保险-06</Channel>  \r\n" + 
            "    <TransSerialno>01005100000130088</TransSerialno> \r\n" + 
            "  </TranslationDTO>  \r\n" + 
            "  <MainContDTOList> \r\n" + 
            "    <MainContDTO> \r\n" + 
            "      <ApplyDate>2018-10-10</ApplyDate>  \r\n" + 
            "      <ApplyTime>11:45:05</ApplyTime>  \r\n" + 
            "      <CvaliDate>2018-10-10</CvaliDate>  \r\n" + 
            "      <CvaliTime>11:45:05</CvaliTime>  \r\n" + 
            "      <EndDate>2019-10-10</EndDate>  \r\n" + 
            "      <EndTime>11:45:05</EndTime>  \r\n" + 
            "      <Prem>4.8</Prem>  \r\n" + 
            "      <MainAmount>300000</MainAmount>  \r\n" + 
            "      <AdditionalAmount/>  \r\n" + 
            "      <ManageCom>86430107</ManageCom>  \r\n" + 
            "      <AgentCom>10005010101</AgentCom>  \r\n" + 
            "      <ContplanCode>S00018</ContplanCode>  \r\n" + 
            "      <MainContDTOSerialno>1</MainContDTOSerialno> \r\n" + 
            "      <MainDevelop>inbankcode,S8600000|inbankaccno,43001780661059666666|inbankaccname, 建行长沙韭菜园支行（收）|paymode,L|tempfeeno, 99199665588</MainDevelop>\r\n" + 
            "    <RemittGrpDTO> \r\n" + 
            "      <GrpName>宝鸡冶峰驾驶员培训学校</GrpName>  \r\n" + 
            "      <Phone>18620385582</Phone>  \r\n" + 
            "      <EMail>1403031090@qq.com</EMail> \r\n" + 
            "      <TaxCode>91610304684787114C</TaxCode>\r\n" + 
            "    </RemittGrpDTO> \r\n" + 
            "    <AppntDTO> \r\n" + 
            "      <Relation1>00</Relation1>  \r\n" + 
            "      <Relation2>00</Relation2>  \r\n" + 
            "      <AppntName>李四T0003</AppntName>  \r\n" + 
            "      <AppntSex>0</AppntSex>  \r\n" + 
            "      <AppntIdtype>0</AppntIdtype>  \r\n" + 
            "      <AppntIdno>430682198607150018</AppntIdno>  \r\n" + 
            "      <AppntBirth>1986-07-15</AppntBirth>  \r\n" + 
            "      <AppntPhone>13786060639</AppntPhone>\r\n" + 
            "      <AppntNativePlace>CHN</AppntNativePlace> \r\n" + 
            "    </AppntDTO>  \r\n" + 
            "    <InsuredList> \r\n" + 
            "      <InsuredDTO> \r\n" + 
            "        <InsuredName>李四T0003</InsuredName>  \r\n" + 
            "        <InsuredSex>0</InsuredSex>  \r\n" + 
            "        <InsuredSex>0</InsuredSex>  \r\n" + 
            "        <InsuredIdtype>0</InsuredIdtype>  \r\n" + 
            "        <InsuredIdno>430682198607150018</InsuredIdno>  \r\n" + 
            "        <InsuredBirth>1986-07-15</InsuredBirth>  \r\n" + 
            "        <MainFlag>1</MainFlag>  \r\n" + 
            "         <InsuredPhone>13786060639</InsuredPhone>\r\n" + 
            "        <RelationToAppnt>00</RelationToAppnt>  \r\n" + 
            "        <InsuredNativePlace>CHN</InsuredNativePlace> \r\n" + 
            "      </InsuredDTO> \r\n" + 
            "    </InsuredList>  \r\n" + 
            "    <BnfList> \r\n" + 
            "      <BnfDTO> \r\n" + 
            "        <BnfFlag>1</BnfFlag> \r\n" + 
            "      </BnfDTO> \r\n" + 
            "    </BnfList> \r\n" + 
            "     </MainContDTO> \r\n" + 
            "      <MainContDTO> \r\n" + 
            "      <ApplyDate>2018-10-10</ApplyDate>  \r\n" + 
            "      <ApplyTime>11:45:05</ApplyTime>  \r\n" + 
            "      <CvaliDate>2018-10-10</CvaliDate>  \r\n" + 
            "      <CvaliTime>11:45:05</CvaliTime>  \r\n" + 
            "      <EndDate>2019-10-10</EndDate>  \r\n" + 
            "      <EndTime>11:45:05</EndTime>  \r\n" + 
            "      <Prem>50</Prem>  \r\n" + 
            "      <MainAmount>320050</MainAmount>  \r\n" + 
            "      <AdditionalAmount/>  \r\n" + 
            "      <ManageCom>86430107</ManageCom>  \r\n" + 
            "      <AgentCom>10005010101</AgentCom>  \r\n" + 
            "      <ContplanCode>S00018</ContplanCode>  \r\n" + 
            "      <MainContDTOSerialno>2</MainContDTOSerialno> \r\n" + 
            "      <MainDevelop>inbankcode,S8600000|inbankaccno,43001780661059666666|inbankaccname, 建行长沙韭菜园支行（收）|paymode,L|tempfeeno, 99199665588</MainDevelop>\r\n" + 
            "    <RemittGrpDTO> \r\n" + 
            "      <GrpName>宝鸡冶峰驾驶员培训学校</GrpName>  \r\n" + 
            "      <Phone>18620385582</Phone>  \r\n" + 
            "      <EMail>1403031090@qq.com</EMail> \r\n" + 
            "      <TaxCode>91610304684787114C</TaxCode>\r\n" + 
            "    </RemittGrpDTO> \r\n" + 
            "    <AppntDTO> \r\n" + 
            "      <Relation1>00</Relation1>  \r\n" + 
            "      <Relation2>00</Relation2>  \r\n" + 
            "      <AppntName>李四T11</AppntName>  \r\n" + 
            "      <AppntSex>0</AppntSex>  \r\n" + 
            "      <AppntIdtype>0</AppntIdtype>  \r\n" + 
            "      <AppntIdno>430682198607150018</AppntIdno>  \r\n" + 
            "      <AppntBirth>1986-07-15</AppntBirth>  \r\n" + 
            "      <AppntPhone>13786060639</AppntPhone>\r\n" + 
            "      <AppntNativePlace>CHN</AppntNativePlace> \r\n" + 
            "    </AppntDTO>  \r\n" + 
            "    <InsuredList> \r\n" + 
            "      <InsuredDTO> \r\n" + 
            "        <InsuredName>李四T11</InsuredName>  \r\n" + 
            "        <InsuredSex>0</InsuredSex>  \r\n" + 
            "        <InsuredSex>0</InsuredSex>  \r\n" + 
            "        <InsuredIdtype>0</InsuredIdtype>  \r\n" + 
            "        <InsuredIdno>430682198607150018</InsuredIdno>  \r\n" + 
            "        <InsuredBirth>1986-07-15</InsuredBirth>  \r\n" + 
            "        <MainFlag>1</MainFlag>  \r\n" + 
            "         <InsuredPhone>13786060639</InsuredPhone>\r\n" + 
            "        <RelationToAppnt>00</RelationToAppnt>  \r\n" + 
            "        <InsuredNativePlace>CHN</InsuredNativePlace> \r\n" + 
            "      </InsuredDTO> \r\n" + 
            "    </InsuredList>  \r\n" + 
            "    <BnfList> \r\n" + 
            "      <BnfDTO> \r\n" + 
            "        <BnfFlag>1</BnfFlag> \r\n" + 
            "      </BnfDTO> \r\n" + 
            "    </BnfList> \r\n" + 
            "     </MainContDTO> \r\n" + 
            "  </MainContDTOList> \r\n" + 
            "</PolicyRequestDTO>\r\n" + 
            "";

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
//        try{
//            String endpoint = "https://tepos.jxlife.com.cn/ter/services/ThdpartyInterface?wsdl";
////            endpoint="http://localhost:8080/ter/services/ThdpartyInterface?wsdl";
////            endpoint="http://220.168.54.231:8088/ter/services/ThdpartyInterface?wsdl";
//
//            
//            Service service = new Service();
//            Call call = (Call)service.createCall();
//            call.setTargetEndpointAddress(new java.net.URL(endpoint));
//            //checkRequest
//            //试算接口
//            call.setOperationName( new  QName( "http://outwardservice.lis.sinosoft.com","underwritingRequest" ));
//
//            String tResult = (String)call.invoke(new Object[] { xml });
//            
//            
//            PolicyRequestDTO dto = new PolicyRequestDTO();
//            
//            
//            
//            
//            System.out.println(tResult);
//        }catch(Exception ex){
//            ex.printStackTrace();
//        }
        
        PolicyRequestDTO dto = setXML();
        try {
            String result = convertToXml(dto, "UTF-8");
            System.out.println(result);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    private static PolicyRequestDTO setXML() {
        
        PolicyRequestDTO dto = new PolicyRequestDTO();
        
        TranslationDTO translationDTO = new TranslationDTO();
        translationDTO.setTransDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        translationDTO.setTransTime(new SimpleDateFormat("HH:mm:ss").format(new Date()));
        translationDTO.setInterfaceType("00");
        translationDTO.setChannel("01009-XXX公司-06");
        translationDTO.setTransSerialno("0100N800000008");
        
        
        //报单主信息
        MainContDTO MainContDTO = new MainContDTO();
        MainContDTO.setApplyDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        MainContDTO.setApplyTime("");
        MainContDTO.setCvaliDate("");
        MainContDTO.setCvaliTime("");
        MainContDTO.setEndDate("");
        MainContDTO.setEndTime("");
        MainContDTO.setPrem(new BigDecimal(10));
        MainContDTO.setMainAmount(new BigDecimal(100));        
        MainContDTO.setManageCom("86430107");
        MainContDTO.setAgentCom("10005010101");
        MainContDTO.setContplanCode("C00021");
        MainContDTO.setMainContDTOSerialno("1");
        
        
        //汇缴单位信息
        RemittGrpDTO RemittGrpDTO = new RemittGrpDTO();
        RemittGrpDTO.setGrpName("湖南鼎安");
        RemittGrpDTO.setHandler("test");
        RemittGrpDTO.setPhone("18911267760");
        RemittGrpDTO.setEMail("380053435@qq.com");
        
        //投保人信息
        AppntDTO AppntDTO = new AppntDTO();
        AppntDTO.setRelation1("00");
        AppntDTO.setRelation2("00");
        AppntDTO.setAppntName("test2");
        AppntDTO.setAppntSex("0");
        AppntDTO.setAppntType("0");
        AppntDTO.setAppntBankAccNo("430421199210208630");
        AppntDTO.setAppntBirth("1992-10-20");
        AppntDTO.setAppntNativePlace("CHN");
        
        //被投保人信息
        InsuredDTO  InsuredDTO = new  InsuredDTO();
        InsuredDTO.setInsuredName("蒋亚球");
        InsuredDTO.setInsuredSex("0");
        InsuredDTO.setInsuredType("0");
        InsuredDTO.setInsuredIdno("430421199210208630");
        InsuredDTO.setInsuredBirth("1992-10-20");
        InsuredDTO.setInsuredNativePlace("CHN");
        InsuredDTO.setMainFlag("1");
        InsuredDTO.setRelationToAppnt("00");
        
//        //收益人信息
//        BnfDTO BnfDTO = new BnfDTO();
//        
        
        dto.setMainContDTO(MainContDTO);
        dto.setRemittGrpDTO(RemittGrpDTO);
        dto.setTranslationDTO(translationDTO);
        
        
        
        
        
        
        
        
        
        return dto;
    }
    
    public static String convertToXml(Object obj, String encoding) throws Exception {
        String result = null;
 
        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = context.createMarshaller();
        // 指定是否使用换行和缩排对已编组 XML 数据进行格式化的属性名称。
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
 
        StringWriter writer = new StringWriter();
        marshaller.marshal(obj, writer);
        result = writer.toString();
 
        return result;
    }

}
