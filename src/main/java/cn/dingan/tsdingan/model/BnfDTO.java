package cn.dingan.tsdingan.model;

import javax.xml.bind.annotation.XmlElement;

public class BnfDTO {
      private String BnfType;//    受益人类型   String  VARCHAR2(1) 可空  "0 -- 生存受益人 1 -- 死亡受益人2 -- 红利受益人，默认空为全部"
      private String BnfFlag ;//   受益人法定标记 String  VARCHAR2(1) 非空  1-法定 
      private Integer BnfOrder;//   受益人顺序   Int     可空  多个受益人时非空，收益人顺序
      private String BnfName;//    受益人姓名   String  VARCHAR2(120)   可空  
      private String BnfSex;// 受益人性别   String  VARCHAR2(1) 可空  0-男；1-女；2-不详
      private String BnfIDType;//  受益人证件类型 String  VARCHAR2(1) 可空  
      private String BnfIDNo;//    受益人证件号码 String  VARCHAR2(20)    可空  
      private String BnfBirthday;//    受益人生日   String  VARCHAR2(20)    可空  格式：YYYY-MM-DD
      private String BnfRelationToInsured;//   受益人与被保人关系   String  VARCHAR2(1) 可空  取值：详见字段可选值
      private String BnfRate;//    受益份额    String  VARCHAR2(4) 可空  录入多个受益人必录，单个受益人默认100%
      private String BnfAddress;// 受益人地址   String  VARCHAR2(100)   可空  
      private String BnfPhone;//   受益人联系电话 String  VARCHAR2(30)    可空  
      private String BnfBankCode;//    受益人银行编码 String  VARCHAR2(10)    可空  
      private String BnfBankAccNo;//   受益人银行账户 String  VARCHAR2(40)    可空  
      private String BnfAccName;// 受益人银行户名 String  VARCHAR2(60)    可空  
      private String BnfDevelop;// 受益人扩展字段 String  VARCHAR2(2000)  可空  扩展字段以“|”分割，字段组合为：key,value
      
      
	public String getBnfType() {
		return BnfType;
	}
	@XmlElement(name = "BnfType")
	public void setBnfType(String bnfType) {
		BnfType = bnfType;
	}
	public String getBnfFlag() {
		return BnfFlag;
	}
	@XmlElement(name = "BnfFlag")
	public void setBnfFlag(String bnfFlag) {
		BnfFlag = bnfFlag;
	}
	public Integer getBnfOrder() {
		return BnfOrder;
	}
	@XmlElement(name = "BnfOrder")
	public void setBnfOrder(Integer bnfOrder) {
		BnfOrder = bnfOrder;
	}
	public String getBnfName() {
		return BnfName;
	}
	@XmlElement(name = "BnfName")
	public void setBnfName(String bnfName) {
		BnfName = bnfName;
	}
	public String getBnfSex() {
		return BnfSex;
	}
	@XmlElement(name = "BnfSex")
	public void setBnfSex(String bnfSex) {
		BnfSex = bnfSex;
	}
	public String getBnfIDType() {
		return BnfIDType;
	}
	@XmlElement(name = "BnfIDType")
	public void setBnfIDType(String bnfIDType) {
		BnfIDType = bnfIDType;
	}
	public String getBnfIDNo() {
		return BnfIDNo;
	}
	@XmlElement(name = "BnfIDNo")
	public void setBnfIDNo(String bnfIDNo) {
		BnfIDNo = bnfIDNo;
	}
	public String getBnfBirthday() {
		return BnfBirthday;
	}
	@XmlElement(name = "BnfBirthday")
	public void setBnfBirthday(String bnfBirthday) {
		BnfBirthday = bnfBirthday;
	}
	public String getBnfRelationToInsured() {
		return BnfRelationToInsured;
	}
	@XmlElement(name = "BnfRelationToInsured")
	public void setBnfRelationToInsured(String bnfRelationToInsured) {
		BnfRelationToInsured = bnfRelationToInsured;
	}
	public String getBnfRate() {
		return BnfRate;
	}
	@XmlElement(name = "BnfRate")
	public void setBnfRate(String bnfRate) {
		BnfRate = bnfRate;
	}
	public String getBnfAddress() {
		return BnfAddress;
	}
	@XmlElement(name = "BnfAddress")
	public void setBnfAddress(String bnfAddress) {
		BnfAddress = bnfAddress;
	}
	public String getBnfPhone() {
		return BnfPhone;
	}
	@XmlElement(name = "BnfPhone")
	public void setBnfPhone(String bnfPhone) {
		BnfPhone = bnfPhone;
	}
	public String getBnfBankCode() {
		return BnfBankCode;
	}
	@XmlElement(name = "BnfBankCode")
	public void setBnfBankCode(String bnfBankCode) {
		BnfBankCode = bnfBankCode;
	}
	public String getBnfBankAccNo() {
		return BnfBankAccNo;
	}
	@XmlElement(name = "BnfBankAccNo")
	public void setBnfBankAccNo(String bnfBankAccNo) {
		BnfBankAccNo = bnfBankAccNo;
	}
	public String getBnfAccName() {
		return BnfAccName;
	}
	@XmlElement(name = "BnfAccName")
	public void setBnfAccName(String bnfAccName) {
		BnfAccName = bnfAccName;
	}
	public String getBnfDevelop() {
		return BnfDevelop;
	}
	@XmlElement(name = "BnfDevelop")
	public void setBnfDevelop(String bnfDevelop) {
		BnfDevelop = bnfDevelop;
	}
      

}
