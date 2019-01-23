package cn.dingan.tsdingan.model;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlElement;

public class AppntDTO {
    private String Relation1;//  与被保人关系  String  VARCHAR2(2) 非空  见字段可选值
    private String Relation2;//   与受益人关系  String  VARCHAR2(2) 非空  见字段可选值
    private String AppntName;//   投保人姓名   String  VARCHAR2(120)   非空  
    private String AppntSex;//    投保人性别   String  VARCHAR2(1) 非空  0-男；1-女；2-不详
    private String AppntIdtype;//     投保人证件类型 String  VARCHAR2(1) 非空  取值：详见字段可选值
    private String AppntIdno;//   投保人证件号码 String  VARCHAR2(20)    非空  
    private String AppntBirth;//  投保人生日   String  VARCHAR2(10)    非空  格式：YYYY-MM-DD
    private String AppntAge;//    投保人年龄   Int NUMBER(3)   可空  
    private String AppntAddress;//    投保人地址   String  VARCHAR2(100)   可空  
    private String AppntPhone;//  投保人联系电话 String  VARCHAR2(30)    可空  
    private String AppntType;//   投保人类型   String  VARCHAR2(1) 可空  1-普通客户；2-股东客户；
    private String AppntNativePlace;//    投保人国籍   String  VARCHAR2(20)    非空  国际国籍简写，默认：CHN-中国
    private String AppntNationality;//    投保人民族   String  VARCHAR2(20)    可空  国际国籍简写，默认：汉族
    private String AppntRgtAddress;//     投保人户口所在地    String  VARCHAR2(80)    可空  
    private String AppntMarriage;//   投保人婚姻状况 String  VARCHAR2(1) 可空  1-未婚；2-已婚；
    private String AppntHealth;//     投保人健康状况 String  VARCHAR2(6) 可空  默认 null
    private BigDecimal AppntStature;//    投保人身高   Float   NUMBER(5,2) 可空  cm为单位
    private BigDecimal AppntAvoirdupois;//    投保人体重   Float   NUMBER(5,2) 可空  kg为单位
    private String AppntDegree;//     投保人学历   String  VARCHAR2(6) 可空  大学，高中，初中，小学
    private String AppntOccupationType;//     投保人职业类别 String  VARCHAR2(10)    可空  
    private String AppntOccupationCode;//     投保人职业代码 String  VARCHAR2(10)    可空  
    private String AppntStartWorkDate;//  投保人参加工作日期   String  VARCHAR2(10)    可空  格式：YYYY-MM-DD
    private String AppntPosition;//   投保人职位   String  VARCHAR2(6) 可空  
    private String AppntBankCode;//   投保人银行编码 String  VARCHAR2(10)    可空  
    private String AppntBankAccNo;//  投保人银行账户 String  VARCHAR2(40)    可空  
    private String AppntAccName;//    投保人银行户名 String  VARCHAR2(60)    可空  
    private String AppntDevelop;//    扩展字段    String  VARCHAR2(20)    可空  
	public String getRelation1() {
		return Relation1;
	}
	@XmlElement(name = "Relation1")
	public void setRelation1(String relation1) {
		Relation1 = relation1;
	}
	public String getRelation2() {
		return Relation2;
	}
	@XmlElement(name = "Relation2")
	public void setRelation2(String relation2) {
		Relation2 = relation2;
	}
	public String getAppntName() {
		return AppntName;
	}
	@XmlElement(name = "AppntName")
	public void setAppntName(String appntName) {
		AppntName = appntName;
	}
	public String getAppntSex() {
		return AppntSex;
	}
	@XmlElement(name = "AppntSex")
	public void setAppntSex(String appntSex) {
		AppntSex = appntSex;
	}
	public String getAppntIdtype() {
		return AppntIdtype;
	}
	@XmlElement(name = "AppntIdtype")
	public void setAppntIdtype(String appntIdtype) {
		AppntIdtype = appntIdtype;
	}
	public String getAppntIdno() {
		return AppntIdno;
	}
	@XmlElement(name = "AppntIdno")
	public void setAppntIdno(String appntIdno) {
		AppntIdno = appntIdno;
	}
	public String getAppntBirth() {
		return AppntBirth;
	}
	@XmlElement(name = "AppntBirth")
	public void setAppntBirth(String appntBirth) {
		AppntBirth = appntBirth;
	}
	public String getAppntAge() {
		return AppntAge;
	}
	public void setAppntAge(String appntAge) {
		AppntAge = appntAge;
	}
	public String getAppntAddress() {
		return AppntAddress;
	}
	public void setAppntAddress(String appntAddress) {
		AppntAddress = appntAddress;
	}
	public String getAppntPhone() {
		return AppntPhone;
	}
	public void setAppntPhone(String appntPhone) {
		AppntPhone = appntPhone;
	}
	public String getAppntType() {
		return AppntType;
	}
	public void setAppntType(String appntType) {
		AppntType = appntType;
	}
	public String getAppntNativePlace() {
		return AppntNativePlace;
	}
	@XmlElement(name = "AppntNativePlace")
	public void setAppntNativePlace(String appntNativePlace) {
		AppntNativePlace = appntNativePlace;
	}
	public String getAppntNationality() {
		return AppntNationality;
	}
	public void setAppntNationality(String appntNationality) {
		AppntNationality = appntNationality;
	}
	public String getAppntRgtAddress() {
		return AppntRgtAddress;
	}
	public void setAppntRgtAddress(String appntRgtAddress) {
		AppntRgtAddress = appntRgtAddress;
	}
	public String getAppntMarriage() {
		return AppntMarriage;
	}
	public void setAppntMarriage(String appntMarriage) {
		AppntMarriage = appntMarriage;
	}
	public String getAppntHealth() {
		return AppntHealth;
	}
	public void setAppntHealth(String appntHealth) {
		AppntHealth = appntHealth;
	}
	public BigDecimal getAppntStature() {
		return AppntStature;
	}
	public void setAppntStature(BigDecimal appntStature) {
		AppntStature = appntStature;
	}
	public BigDecimal getAppntAvoirdupois() {
		return AppntAvoirdupois;
	}
	public void setAppntAvoirdupois(BigDecimal appntAvoirdupois) {
		AppntAvoirdupois = appntAvoirdupois;
	}
	public String getAppntDegree() {
		return AppntDegree;
	}
	public void setAppntDegree(String appntDegree) {
		AppntDegree = appntDegree;
	}
	public String getAppntOccupationType() {
		return AppntOccupationType;
	}
	public void setAppntOccupationType(String appntOccupationType) {
		AppntOccupationType = appntOccupationType;
	}
	public String getAppntOccupationCode() {
		return AppntOccupationCode;
	}
	public void setAppntOccupationCode(String appntOccupationCode) {
		AppntOccupationCode = appntOccupationCode;
	}
	public String getAppntStartWorkDate() {
		return AppntStartWorkDate;
	}
	public void setAppntStartWorkDate(String appntStartWorkDate) {
		AppntStartWorkDate = appntStartWorkDate;
	}
	public String getAppntPosition() {
		return AppntPosition;
	}
	public void setAppntPosition(String appntPosition) {
		AppntPosition = appntPosition;
	}
	public String getAppntBankCode() {
		return AppntBankCode;
	}
	public void setAppntBankCode(String appntBankCode) {
		AppntBankCode = appntBankCode;
	}
	public String getAppntBankAccNo() {
		return AppntBankAccNo;
	}
	public void setAppntBankAccNo(String appntBankAccNo) {
		AppntBankAccNo = appntBankAccNo;
	}
	public String getAppntAccName() {
		return AppntAccName;
	}
	public void setAppntAccName(String appntAccName) {
		AppntAccName = appntAccName;
	}
	public String getAppntDevelop() {
		return AppntDevelop;
	}
	public void setAppntDevelop(String appntDevelop) {
		AppntDevelop = appntDevelop;
	}

}
