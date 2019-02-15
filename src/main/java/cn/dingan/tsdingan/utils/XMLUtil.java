package cn.dingan.tsdingan.utils;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import com.thoughtworks.xstream.XStream;

import cn.dingan.tsdingan.response.PolicyResponseDTO;
import cn.dingan.tsdingan.response.ResopnseMainContDTO;
import cn.dingan.tsdingan.response.ResponsePolicyRequestDTO;


/**
 * 
* @ClassName: XMLUtil
* @Description: xml格式化工具类
* @author jyq#trasen.cn
* @date 2019年2月12日 下午5:16:55
*
 */
public class XMLUtil {
    
    public static String convertToXml(Object obj, Class<?> load) throws Exception {
        JAXBContext context = JAXBContext.newInstance(load);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "GBK");
        StringWriter writer = new StringWriter();
        marshaller.marshal(obj,writer);
        return writer.toString();

  }
    
    public static void main(String [] args) {
        String xml = "<?xml version=\"1.0\" encoding=\"GBK\"?>"
                + "<PolicyResponseDTO>"
                + "<PolicyRequestDTO><TransDate>2019-02-15</TransDate><TransTime>15:21:05</TransTime><TransSerialno>01005100000028</TransSerialno>"
                + "</PolicyRequestDTO>"
                + "<MainContDTOList>"
                + "<MainContDTO>"
                + "<ReTranDate>2019-02-15</ReTranDate><ReTranTime>15:21:05</ReTranTime><MainContDTOSerialno>1</MainContDTOSerialno>"
                + "<SumPrem>10.0</SumPrem><Flag>0</Flag><Desc>成功</Desc>"
                + "</MainContDTO>"
                + "<MainContDTO>"
                + "<ReTranDate>2019-02-15</ReTranDate><ReTranTime>15:21:05</ReTranTime><MainContDTOSerialno>1</MainContDTOSerialno>"
                + "<SumPrem>10.0</SumPrem><Flag>0</Flag><Desc>成功</Desc>"
                + "</MainContDTO>"
                + "</MainContDTOList>"
                + "</PolicyResponseDTO>";
        
        XStream xStream = new XStream();
        xStream.alias("PolicyRequestDTO", ResponsePolicyRequestDTO.class);
        xStream.alias("MainContDTO", ResopnseMainContDTO.class);
        xStream.alias("PolicyResponseDTO", PolicyResponseDTO.class);
        PolicyResponseDTO policyResponseDTO = (PolicyResponseDTO) xStream.fromXML(xml);
        
         System.out.println(policyResponseDTO);
    }
    
    /**
     * 
    * @Title: getResult
    * @Description: 解析请求返回xml转对象
    * @param @param resultXML
    * @param @return    参数
    * @return PolicyResponseDTO    返回类型
    * @throws
    * @author jyq#trasen.cn
    * @date 2019年2月15日 下午4:48:51
     */
    public static PolicyResponseDTO getResult(String resultXML) {
        XStream xStream = new XStream();
        xStream.alias("PolicyRequestDTO", ResponsePolicyRequestDTO.class);
        xStream.alias("MainContDTO", ResopnseMainContDTO.class);
        xStream.alias("PolicyResponseDTO", PolicyResponseDTO.class);
        PolicyResponseDTO policyResponseDTO = (PolicyResponseDTO) xStream.fromXML(resultXML);
        return policyResponseDTO;
    }
    
     
}
