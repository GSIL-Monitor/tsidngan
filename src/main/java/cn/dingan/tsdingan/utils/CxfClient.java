package cn.dingan.tsdingan.utils;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    
	
	final static String xml ="<?xml version='1.0'encoding='GBK'><PolicyRequestDTO><MainContDTOList><MainContDTO><AdditionalAmount>0</AdditionalAmount><AgentCom>10005010101</AgentCom><ApplyDate>2019-02-17</ApplyDate><ApplyTime>20:19:39</ApplyTime><AppntDTO><AppntBirth>1992-10-20</AppntBirth><AppntIdno>430421199210208630</AppntIdno><AppntIdtype>0</AppntIdtype><AppntName>子夜</AppntName><AppntNativePlace>CHN</AppntNativePlace><AppntSex>M</AppntSex><Relation1>00</Relation1><Relation2>00</Relation2></AppntDTO><BnfList><BnfDTO><BnfFlag>1</BnfFlag></BnfDTO></BnfList><ContplanCode>S00031</ContplanCode><CvaliDate>2018-06-06</CvaliDate><CvaliTime>11:15:19</CvaliTime><EndDate>2019-06-06</EndDate><EndTime>11:15:19</EndTime><InsuredList><InsuredDTO><InsuredBirth>1992-10-20</InsuredBirth><InsuredIdno>430421199210208630</InsuredIdno><InsuredIdtype>0</InsuredIdtype><InsuredName>子夜</InsuredName><InsuredNativePlace>CHN</InsuredNativePlace><InsuredSex>M</InsuredSex><MainFlag>1</MainFlag><RelationToAppnt>00</RelationToAppnt></InsuredDTO></InsuredList><MainAmount>550420</MainAmount><MainContDTOSerialno>1</MainContDTOSerialno><MainDevelop>inbankcode,S8600000|inbankaccno,43001780661059666666|inbankaccname,建行长沙韭菜园支行（收）|paymode,L|tempfeeno,1234567899</MainDevelop><ManageCom>86430107</ManageCom><Prem>10</Prem><RemittGrpDTO><EMail>459942733@qq.com</EMail><GrpName>子夜测试</GrpName><Phone>18163669369</Phone></RemittGrpDTO></MainContDTO></MainContDTOList><TranslationDTO><Channel>01005-鼎安经纪保险-06</Channel><InterfaceType>00</InterfaceType><TransTime>2019-02-17</TransTime><TransSerialno>01005100000028</TransSerialno><TransTime>20:19:39</TransTime></TranslationDTO></PolicyRequestDTO>";

	
	final static String fpxml = "<?xml version=\"1.0\" encoding=\"GBK\"?>\r\n" + 
	        "\r\n" + 
	        "<RequestDTO> \r\n" + 
	        "  <TranslationDTO> \r\n" + 
	        "    <systemType>01</systemType>  \r\n" + 
	        "    <interfaceType>20</interfaceType>  \r\n" + 
	        "    <syncType>0</syncType>  \r\n" + 
	        "    <transDate>2018-03-26</transDate>  \r\n" + 
	        "    <transTime>15:01:42</transTime>  \r\n" + 
	        "    <transSerialno>99999991537427645846</transSerialno>  \r\n" + 
	        "    <transCount>3</transCount>  \r\n" + 
	        "    <transOperator>hndajj</transOperator> \r\n" + 
	        "  </TranslationDTO>  \r\n" + 
	        "  <InvoiceDTOList> \r\n" + 
	        "    <InvoiceDTO> \r\n" + 
	        "      <invoiceNo>FP01005100000104</invoiceNo>  \r\n" + 
	        "      <invoiceType>026</invoiceType>  \r\n" + 
	        "      <taxpayerType>01</taxpayerType>  \r\n" + 
	        "      <invoiceName>2353253</invoiceName>  \r\n" + 
	        "      <taxpayerID>18620385582</taxpayerID>  \r\n" + 
	        "      <invoiceEmail>380053453@qq.com</invoiceEmail>  \r\n" + 
	        "      <idType>8</idType>  \r\n" + 
	        "      <idNo>18620385582</idNo>  \r\n" + 
	        "      <ePolicyDTOList> \r\n" + 
	        "        <ePolicyDTO> \r\n" + 
	        "          <otherNoType>01</otherNoType>  \r\n" + 
	        "          <otherNo>01005100000020005</otherNo> \r\n" + 
	        "        </ePolicyDTO> \r\n" + 
	        "         <ePolicyDTO> \r\n" + 
	        "          <otherNoType>01</otherNoType>  \r\n" + 
	        "          <otherNo>01005100000020006</otherNo> \r\n" + 
	        "        </ePolicyDTO> \r\n" + 
	        "         <ePolicyDTO> \r\n" + 
	        "          <otherNoType>01</otherNoType>  \r\n" + 
	        "          <otherNo>010050100001008</otherNo> \r\n" + 
	        "        </ePolicyDTO> \r\n" + 
	        "      </ePolicyDTOList> \r\n" + 
	        "    </InvoiceDTO> \r\n" + 
	        "  </InvoiceDTOList>\r\n" + 
	        "</RequestDTO>\r\n" + 
	        ""; 
    

    /**
     * @param args
     */
    public static void main(String[] args) {
        try{
        	
            String endpoint = "https://tepos.jxlife.com.cn/ter/services/ThdpartyInterface?wsdl";
            
            Service service = new Service();
            Call call = (Call)service.createCall();
            call.setTargetEndpointAddress(new java.net.URL(endpoint));
            
            call.setOperationName(new QName("http://outwardservice.lis.sinosoft.com","addInvoiceInfPush"));
            
            
            String tResult = (String)call.invoke(new Object[] {  fpxml });
            
            System.out.println(tResult);
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
    }
    
    public static void trytest() throws Exception{
        PolicyRequestDTO dto = setXML();
        
        String xml2 = convertToXml(dto,PolicyRequestDTO.class );
        
        xml2 = xml2.replace("standalone=\"yes\"", "");
        
        System.out.println(xml2);
        
        
//      System.out.println(xml);
//      
        
        String endpoint = "https://tepos.jxlife.com.cn/ter/services/ThdpartyInterface?wsdl";
        
        Service service = new Service();
        Call call = (Call)service.createCall();
        call.setTargetEndpointAddress(new java.net.URL(endpoint));
        //checkRequest
        //试算接口
        call.setOperationName( new  QName("http://outwardservice.lis.sinosoft.com","underwritingRequest"));

        String tResult = (String)call.invoke(new Object[] {  xml2 });
        
        System.out.println(tResult);
    }
    
    private static PolicyRequestDTO setXML() {
        
        PolicyRequestDTO dto = new PolicyRequestDTO();
        
        
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sf1 = new SimpleDateFormat("HH:mm:ss");
        
        String today = sf.format(new Date());
        
        String time = sf1.format(new Date());
        
        
        //保险公司信息
        TranslationDTO TranslationDTO = new TranslationDTO();
        TranslationDTO.setTransDate(today);
        TranslationDTO.setTransTime(time);
        TranslationDTO.setInterfaceType("00");//00试算  01 承保
        TranslationDTO.setChannel("01005-鼎安经纪保险-06");//渠道 怎么获取
        TranslationDTO.setTransSerialno("010050100009008");//流水号 怎么设置
        
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
        AppntDTO.setAppntIdno("430421199210208630");
        AppntDTO.setAppntName("蒋亚球");
        AppntDTO.setRelation1("00");
        AppntDTO.setRelation2("00");
        AppntDTO.setAppntBirth("1992-10-20");
        AppntDTO.setAppntIdtype("0");
        AppntDTO.setAppntNativePlace("CHN");
        
        MainContDTO.setAppntDTO(AppntDTO);
        
        MainContDTO.setApplyDate(today);
        MainContDTO.setApplyTime(time);
        MainContDTO.setCvaliDate(today);
        MainContDTO.setCvaliTime(time);
        MainContDTO.setManageCom("86430107");//管理机构 在售票系统中维护，以保险公司提供的为准 
        MainContDTO.setMainAmount(new BigDecimal(550420));//保额是从设置的
        
        //被投保人信息列表
        InsuredList InsuredList = new InsuredList();
        List<InsuredDTO> InsuredDTOS = new ArrayList<>();
        //被投保人信息
        InsuredDTO InsuredDTO = new InsuredDTO();
        InsuredDTO.setMainFlag("1");
        InsuredDTO.setInsuredSex("0");
        InsuredDTO.setInsuredIdno("430421199210208630");
        InsuredDTO.setInsuredName("蒋亚球");
        InsuredDTO.setInsuredBirth("1992-10-20");
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
