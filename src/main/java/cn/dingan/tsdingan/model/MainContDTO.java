package cn.dingan.tsdingan.model;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlElement;

public class MainContDTO {
    
    private String ApplyDate;//投保日期
    
    private String ApplyTime;//投保时间
    
    private String CvaliDate;//生效日期

    
    private String CvaliTime;//生效时间

    
    private String EndDate;//终止日期

    
    private String EndTime;//终止时间

    
    private String CertifyNo;//单证号码

    
    private BigDecimal Prem;//保费      套餐总保费，售票系统维护里程与保费关系


    
    private BigDecimal MainAmount;//保额     套餐总保费，售票系统维护里程与保额关系


    
    
    private BigDecimal AdditionalAmount;//附加险保额      套餐总保费，售票系统维护里程与附加险保额关系


    
    private String ManageCom;//管理机构       在售票系统中维护，以保险公司提供的为准


    
    private String AgentCom;//代理机构编码     在售票系统中维护，以保险公司提供的为准


    
    private String ContplanCode; //套餐编码
//    "
    
    private String MainDevelop;//"（试算时，可不传）新增支付方式：L-在线支付 。鼎安经纪约定如下：
//  inbankcode,S8600000|inbankaccno,43001780661059666666|inbankaccname, 建行长沙韭菜园支行（收）|paymode,L|tempfeeno, 支付流水号(对应银行转账的流水号)
    
    private String MainContDTOSerialno; //1-100 序号
    
    
    private BnfList BnfList;
    
    
    private AppntDTO AppntDTO;
    
    private InsuredList  InsuredList;
    
    private RemittGrpDTO RemittGrpDTO;

	public String getApplyDate() {
		return ApplyDate;
	}

	@XmlElement(name = "ApplyDate")
	public void setApplyDate(String applyDate) {
		ApplyDate = applyDate;
	}

	public String getApplyTime() {
		return ApplyTime;
	}

	@XmlElement(name = "ApplyTime")
	public void setApplyTime(String applyTime) {
		ApplyTime = applyTime;
	}

	public String getCvaliDate() {
		return CvaliDate;
	}

	@XmlElement(name = "CvaliDate")
	public void setCvaliDate(String cvaliDate) {
		CvaliDate = cvaliDate;
	}

	public String getCvaliTime() {
		return CvaliTime;
	}

	@XmlElement(name = "CvaliTime")
	public void setCvaliTime(String cvaliTime) {
		CvaliTime = cvaliTime;
	}

	public String getEndDate() {
		return EndDate;
	}

	@XmlElement(name = "EndDate")
	public void setEndDate(String endDate) {
		EndDate = endDate;
	}

	public String getEndTime() {
		return EndTime;
	}

	@XmlElement(name = "EndTime")
	public void setEndTime(String endTime) {
		EndTime = endTime;
	}

	public String getCertifyNo() {
		return CertifyNo;
	}

	@XmlElement(name = "CertifyNo")
	public void setCertifyNo(String certifyNo) {
		CertifyNo = certifyNo;
	}

	public BigDecimal getPrem() {
		return Prem;
	}
	@XmlElement(name = "Prem")
	public void setPrem(BigDecimal prem) {
		Prem = prem;
	}

	public BigDecimal getMainAmount() {
		return MainAmount;
	}

	@XmlElement(name = "MainAmount")
	public void setMainAmount(BigDecimal mainAmount) {
		MainAmount = mainAmount;
	}

	public BigDecimal getAdditionalAmount() {
		return AdditionalAmount;
	}

	@XmlElement(name = "AdditionalAmount")
	public void setAdditionalAmount(BigDecimal additionalAmount) {
		AdditionalAmount = additionalAmount;
	}

	public String getManageCom() {
		return ManageCom;
	}

	@XmlElement(name = "ManageCom")
	public void setManageCom(String manageCom) {
		ManageCom = manageCom;
	}

	public String getAgentCom() {
		return AgentCom;
	}

	
	@XmlElement(name = "AgentCom")
	public void setAgentCom(String agentCom) {
		AgentCom = agentCom;
	}

	public String getContplanCode() {
		return ContplanCode;
	}

	@XmlElement(name = "ContplanCode")
	public void setContplanCode(String contplanCode) {
		ContplanCode = contplanCode;
	}

	public String getMainDevelop() {
		return MainDevelop;
	}

	@XmlElement(name = "MainDevelop")
	public void setMainDevelop(String mainDevelop) {
		MainDevelop = mainDevelop;
	}

	public String getMainContDTOSerialno() {
		return MainContDTOSerialno;
	}

	@XmlElement(name = "MainContDTOSerialno")
	public void setMainContDTOSerialno(String mainContDTOSerialno) {
		MainContDTOSerialno = mainContDTOSerialno;
	}

	 
	public BnfList getBnfList() {
		return BnfList;
	}

	@XmlElement(name = "BnfList")
	public void setBnfList(BnfList bnfList) {
		BnfList = bnfList;
	}

	public AppntDTO getAppntDTO() {
		return AppntDTO;
	}

	@XmlElement(name = "AppntDTO")
	public void setAppntDTO(AppntDTO appntDTO) {
		AppntDTO = appntDTO;
	}

	public RemittGrpDTO getRemittGrpDTO() {
		return RemittGrpDTO;
	}

	
	public InsuredList getInsuredList() {
		return InsuredList;
	}

	@XmlElement(name = "InsuredList")
	public void setInsuredList(InsuredList insuredList) {
		InsuredList = insuredList;
	}

	@XmlElement(name = "RemittGrpDTO")
	public void setRemittGrpDTO(RemittGrpDTO remittGrpDTO) {
		RemittGrpDTO = remittGrpDTO;
	}
}
