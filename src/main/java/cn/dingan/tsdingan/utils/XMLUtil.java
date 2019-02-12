package cn.dingan.tsdingan.utils;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;


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
}
