package cn.dingan.tsdingan.model;

import javax.xml.bind.annotation.XmlElement;

public class TranslationDTO {
    
    private String TransDate;//请求报文交易日期
    
    private String TransTime;//请求报文交易时间
    
    private String InterfaceType;//请求报文接口类型 可选值00-试算；01-承保；（必须先试算，再承保）

    
    private String Channel;//销售渠道
    
    private String TransSerialno;//批次号 由保险公司提供 如：01009-XXX公司-06。
    
    private String TransDevelop;

	public String getTransDate() {
		return TransDate;
	}
	@XmlElement(name = "TransTime")
	public void setTransDate(String transDate) {
		TransDate = transDate;
	}

	public String getTransTime() {
		return TransTime;
	}
	@XmlElement(name = "TransTime")
	public void setTransTime(String transTime) {
		TransTime = transTime;
	}

	public String getInterfaceType() {
		return InterfaceType;
	}
	@XmlElement(name = "InterfaceType")
	public void setInterfaceType(String interfaceType) {
		InterfaceType = interfaceType;
	}

	public String getChannel() {
		return Channel;
	}
	@XmlElement(name = "Channel")
	public void setChannel(String channel) {
		Channel = channel;
	}

	public String getTransSerialno() {
		return TransSerialno;
	}
	@XmlElement(name = "TransSerialno")
	public void setTransSerialno(String transSerialno) {
		TransSerialno = transSerialno;
	}

	public String getTransDevelop() {
		return TransDevelop;
	}
	@XmlElement(name = "TransDevelop")
	public void setTransDevelop(String transDevelop) {
		TransDevelop = transDevelop;
	}
}
