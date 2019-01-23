package cn.dingan.tsdingan.utils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

import cn.dingan.tsdingan.model.AppntDTO;
import cn.dingan.tsdingan.model.BnfDTO;
import cn.dingan.tsdingan.model.InsuredDTO;
import cn.dingan.tsdingan.model.MainContDTO;
import cn.dingan.tsdingan.model.PolicyRequestDTO;
import cn.dingan.tsdingan.model.RemittGrpDTO;
import cn.dingan.tsdingan.model.TranslationDTO; 

public class CxfClient {
    
    final static String xml =

            "<?xml version=\"1.0\" encoding=\"GBK\"?><PolicyRequestDTO><TranslationDTO><TransDate>2018-03-30</TransDate><TransTime>12:25:08</TransTime><TransSerialno></TransSerialno><TransDevelop>01006-车站出单-03</TransDevelop></TranslationDTO><MainContDTO><ApplyDate>2018-03-30</ApplyDate><ApplyTime>12:25:08</ApplyTime><CvaliDate>2018-03-30</CvaliDate><CvaliTime>12:25:08</CvaliTime><EndDate>2018-04-01</EndDate><EndTime>12:25:08</EndTime><CertifyNo></CertifyNo><Prem>3</Prem><MainAmount>68000</MainAmount><AdditionalAmount>0</AdditionalAmount><ManageCom>86430107</ManageCom><AgentCom>10005010101</AgentCom><ContplanCode>C00021</ContplanCode></MainContDTO><TrafficDTO><TrainNo></TrainNo><TicketNo>T45454523</TicketNo><SeatNo>25</SeatNo><SellDate>2018-03-30</SellDate><SellTime>12:25:08</SellTime><OperCode>SELL001</OperCode></TrafficDTO><AppntDTO><Relation1>00</Relation1><Relation2>00</Relation2><AppntName>zzzp</AppntName><AppntSex>0</AppntSex><AppntIdtype>1</AppntIdtype><AppntIdno>A457654343</AppntIdno><AppntBirth>1992-06-18</AppntBirth><AppntDevelop></AppntDevelop></AppntDTO><InsuredList><InsuredDTO><InsuredName></InsuredName><InsuredSex></InsuredSex><InsuredIdtype></InsuredIdtype><InsuredIdno></InsuredIdno><InsuredBirth></InsuredBirth><MainFlag></MainFlag><RelationToMainInsured></RelationToMainInsured><RelationToAppnt></RelationToAppnt></InsuredDTO></InsuredList><BnfList><BnfDTO><BnfType></BnfType><BnfFlag></BnfFlag><BnfOrder></BnfOrder><BnfName></BnfName><BnfSex></BnfSex><BnfIDType></BnfIDType><BnfIDNo></BnfIDNo><BnfBirthday></BnfBirthday><BnfRelationToInsured></BnfRelationToInsured></BnfDTO></BnfList></PolicyRequestDTO>";

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
            String endpoint = "https://tepos.jxlife.com.cn/ter/services/ThdpartyInterface?wsdl";
//            endpoint="http://localhost:8080/ter/services/ThdpartyInterface?wsdl";
//            endpoint="http://220.168.54.231:8088/ter/services/ThdpartyInterface?wsdl";

            
            Service service = new Service();
            Call call = (Call)service.createCall();
            call.setTargetEndpointAddress(new java.net.URL(endpoint));
            //checkRequest
            call.setOperationName( new  QName( "http://outwardservice.lis.sinosoft.com","underwritingRequest" ));

            String tResult = (String)call.invoke(new Object[] { xml });
            
            
            PolicyRequestDTO dto = new PolicyRequestDTO();
            
            
            
            
            System.out.println(tResult);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    private String setXML() {
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
        
        //收益人信息
        BnfDTO BnfDTO = new BnfDTO();
        
        
        
        
        
        
        
        
        
        
        
        
        return "";
    }
}
