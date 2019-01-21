package cn.dingan.tsdingan.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class RemittGrpDTO {
    
    private String GrpName;//   单位名称    String  VARCHAR2(200)   非空  单位名称
    private String BusinessType;//   行业分类    String  VARCHAR2(20)    可空  
    private String GrpNature;//  单位性质    String  VARCHAR2(10)    可空  
    private String UnifiedSocialCreditCode;//    统一社会信用代码    String  VARCHAR2(20)    可空  统一社会信用代码、税务登记号码、营业执照编码、团体组织机构代码四者至少录其一
    private String TaxCode;//    税务登记号码  String  VARCHAR2(20)    可空  
    private String TaxEffSTARTDATE;//    税务登记证有效期起   String  VARCHAR2(10)    可空  格式：YYYY-MM-DD
    private String TaxEffENDDATE;//  税务登记证有效期止   String  VARCHAR2(10)    可空  格式：YYYY-MM-DD
    private String LicenseCode;//   营业执照编码  String  VARCHAR2(40)    可空  
    private String LicEffStartDate;//    营业执照有效期起    String  VARCHAR2(10)    可空  格式：YYYY-MM-DD
    private String LicEffEndDate;//  营业执照有效期止    String  VARCHAR2(10)    可空  格式：YYYY-MM-DD
    private String OrganCode;//  团体组织机构代码    String  VARCHAR2(30)    可空  
    private String OrgCEffStartDate;//   组织机构代码有效期起  String  VARCHAR2(10)    可空  格式：YYYY-MM-DD
    private String OrgCEffEndDate;// 组织机构代码有效期止  String  VARCHAR2(10)    可空  格式：YYYY-MM-DD
    private String SocialInsuNo;//   单位社保登记号 String  VARCHAR2(20)    可空  
    private String TaxType;//    发票类型    String  VARCHAR2(30)    可空  004-专票  007-普票  026-电子票
    private String TaxPayerType;//   纳税人类型   String  VARCHAR2(3) 可空  01-一般纳税人  02-非一般纳税人 ； TaxType=004 时，该字段必须为01
    private String Handler;//    经办人 String  VARCHAR2(20)    非空  
    private String GrpZipCode;// 邮政编码    String  VARCHAR2(10)    可空  
    private String Province;//   省   String  VARCHAR2(20)    可空  TaxType=004 时，必录
    private String City;//   市   String  VARCHAR2(20)    可空  TaxType=004 时，必录
    private String County;// 区/县 String  VARCHAR2(40)    可空  TaxType=004 时，必录
    private String DetailAddress;//  详细地址    String  VARCHAR2(80)    可空  TaxType=004 时，必录   如无，则录手机号
    private String TelPhone;//   单位电话    String  VARCHAR2(18)    可空  TaxType=004 时，必录
    private String Phone;//  手机号 String  VARCHAR2(18)    非空  
    private String EMail;//  电子邮箱    String  VARCHAR2(20)    非空  电子邮箱，驾校投保确认书接收邮箱
    private String BankNodeName;//   开户行及网点名称    String  VARCHAR2(120)   可空  TaxType=004 时，必录
    private String BankAccName;//    银行账户名   String  VARCHAR2(120)   可空  TaxType=004 时，必录
    private String BankAccNo;//  银行帐号    String  VARCHAR2(40)    可空  TaxType=004 时，必录
    private Integer Peoples;//    总人数 Int NUMBER(10)  可空  

    
    private String OnWorkPeoples;// 在职人数    Int NUMBER(10)  可空  
    private String OffWorkPeoples;// 退休人数    Int NUMBER(10)  可空  
    private String OtherPeoples;//   其它人员人数  Int NUMBER(10)  可空  
    private BigDecimal RgtMoney;//   注册资本    Float   NUMBER(16,2)    可空  
    private BigDecimal Asset;//  资产总额    Float   NUMBER(16,2)    可空  
    private BigDecimal NetProfitRate;//  净资产收益率  Float   NUMBER(16,4)    可空  
    private String Satrap;// 负责人 String  VARCHAR2(120)   可空  
    private String SatrapIDType;//   负责人证件类型 String  VARCHAR2(1) 可空  
    private String SatrapIDNo;// 负责人证件号码 String  VARCHAR2(20)    可空  
    private String SatrapIDEffStartDate;//   负责人证件有效期起   String  VARCHAR2(10)    可空  
    private String SatrapIDEffEndDate;// 负责人证件有效期止   String  VARCHAR2(10)    可空  
    private String SatrapPhone;//    负责人联系电话 String  VARCHAR2(18)    可空  
    private String SatrapEmail;//    负责人电子邮件 String  VARCHAR2(20)    可空  
    private String CorporationsName;//   法人姓名    String  VARCHAR2(512)   可空  
    private String CorporationsIDType;// 法人身份证件类型    String  VARCHAR2(32)    可空  
    private String CorporationsOtherType;//  法人其他身份证件/证明文件类型 String  VARCHAR2(32)    可空  
    private String CorporationsIDNo;//   法人身份证件号码    String  VARCHAR2(128)   可空  
    private String StockholderName;//    控股股东或实际控制人名称    String  VARCHAR2(512)   可空  
    private String StockholderIDType;//  控股股东或实际控制人证件类型  String  VARCHAR2(6) 可空  
    private String StockholderOtherType;//   控股股东其他证件类型  String  VARCHAR2(32)    可空  
    private String StockholderIDNo;//    控股股东或实际控制人证件号码  String  VARCHAR2(128)   可空  
    private String RemittGrpDevelop;//   汇缴单位扩展字段    String  VARCHAR2(2000)  可空  扩展字段以“|”分割，字段组合为：key,value

}
