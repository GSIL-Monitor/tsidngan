package cn.dingan.test;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class WebServiceTest {
    
     public String xml =

            "<?xml version=\"1.0\" encoding=\"GBK\"?><PolicyRequestDTO><TranslationDTO><TransDate>2018-03-30</TransDate><TransTime>12:25:08</TransTime><TransSerialno></TransSerialno><TransDevelop>01006-车站出单-03</TransDevelop></TranslationDTO><MainContDTO><ApplyDate>2018-03-30</ApplyDate><ApplyTime>12:25:08</ApplyTime><CvaliDate>2018-03-30</CvaliDate><CvaliTime>12:25:08</CvaliTime><EndDate>2018-04-01</EndDate><EndTime>12:25:08</EndTime><CertifyNo></CertifyNo><Prem>3</Prem><MainAmount>68000</MainAmount><AdditionalAmount>0</AdditionalAmount><ManageCom>86430107</ManageCom><AgentCom>10005010101</AgentCom><ContplanCode>C00021</ContplanCode></MainContDTO><TrafficDTO><TrainNo></TrainNo><TicketNo>T45454523</TicketNo><SeatNo>25</SeatNo><SellDate>2018-03-30</SellDate><SellTime>12:25:08</SellTime><OperCode>SELL001</OperCode></TrafficDTO><AppntDTO><Relation1>00</Relation1><Relation2>00</Relation2><AppntName>zzzp</AppntName><AppntSex>0</AppntSex><AppntIdtype>1</AppntIdtype><AppntIdno>A457654343</AppntIdno><AppntBirth>1992-06-18</AppntBirth><AppntDevelop></AppntDevelop></AppntDTO><InsuredList><InsuredDTO><InsuredName></InsuredName><InsuredSex></InsuredSex><InsuredIdtype></InsuredIdtype><InsuredIdno></InsuredIdno><InsuredBirth></InsuredBirth><MainFlag></MainFlag><RelationToMainInsured></RelationToMainInsured><RelationToAppnt></RelationToAppnt></InsuredDTO></InsuredList><BnfList><BnfDTO><BnfType></BnfType><BnfFlag></BnfFlag><BnfOrder></BnfOrder><BnfName></BnfName><BnfSex></BnfSex><BnfIDType></BnfIDType><BnfIDNo></BnfIDNo><BnfBirthday></BnfBirthday><BnfRelationToInsured></BnfRelationToInsured></BnfDTO></BnfList></PolicyRequestDTO>";
    public void test() throws Exception {
        String endpoint = "http://tepos.jxlife.com.cn/ter/services/ThdpartyInterface?wsdl";
//      endpoint="http://localhost:8080/ter/services/ThdpartyInterface?wsdl";
//      endpoint="http://220.168.54.231:8088/ter/services/ThdpartyInterface?wsdl";

      
      Service service = new Service();
      Call call = (Call)service.createCall();
      call.setTargetEndpointAddress(new java.net.URL(endpoint));
      //checkRequest
      call.setOperationName( new  QName( "http://outwardservice.lis.sinosoft.com","checkRequest" ));

      String tResult = (String)call.invoke(new Object[] { xml });
      System.out.println(tResult);
    }
}
