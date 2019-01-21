package cn.dingan.tsdingan.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class InsuredDTO {
    private String InsuredName;//    被保人姓名   String  VARCHAR2(20)    非空  
    private String InsuredSex;// 被保人性别   String  VARCHAR2(1) 非空  0-男；1-女；2-不详
    private String InsuredIdtype;//  被保人证件类型 String  VARCHAR2(1) 非空  取值：详见字段可选值
    private String InsuredIdno;//    被保人证件号码 String  VARCHAR2(20)    非空  
    private String InsuredBirth;//   被保人生日   String  VARCHAR2(20)    非空  格式：YYYY-MM-DD
    private String MainFlag;//   主副被保险人标记    String  VARCHAR2(1) 非空  默认：1
    private String RelationToMainInsured;//  主副被保险人标记    String  VARCHAR2(2) 可空  
    private String RelationToAppnt;//    与投保人关系  String  VARCHAR2(2) 非空  默认：00
    private Integer InsuredAge;// 被保人年龄   Int NUMBER(3)   可空  
    private String InsuredAddress;// 被保人地址   String  VARCHAR2(100)   可空  
    private String InsuredPhone;//   被保人联系电话 String  VARCHAR2(30)    可空  
    private String InsuredType;//    被保人类型   String  VARCHAR2(1) 可空  1-普通客户；2-股东客户；
    private String InsuredNativePlace;// 被保人国籍   String  VARCHAR2(20)    非空  国际国籍简写，默认：CHN-中国
    private String InsuredNationality;// 被保人民族   String  VARCHAR2(20)    可空  国际国籍简写，默认：汉族
    private String InsuredRgtAddress;//  被保人户口所在地    String  VARCHAR2(80)    可空  
    private String InsuredMarriage;//    被保人婚姻状况 String  VARCHAR2(1) 可空  1-未婚；2-已婚；
    private String InsuredHealth;//  被保人健康状况 String  VARCHAR2(6) 可空  默认 null
    private BigDecimal InsuredStature;// 被保人身高   Float   NUMBER(5,2) 可空  cm为单位
    private BigDecimal InsuredAvoirdupois;// 被保人体重   Float   NUMBER(5,2) 可空  kg为单位
    private String InsuredDegree;//  被保人学历   String  VARCHAR2(6) 可空  大学，高中，初中，小学
    private String InsuredOccupationType;//  被保人职业类别 String  VARCHAR2(10)    可空  
    private String InsuredOccupationCode;//  被保人职业代码 String  VARCHAR2(10)    可空  
    private String InsuredStartWorkDate;//   被保人参加工作日期   String  VARCHAR2(10)    可空  格式：YYYY-MM-DD
    private String InsuredPosition;//    被保人职位   String  VARCHAR2(6) 可空  
    private String InsuredBankCode;//    被保人银行编码 String  VARCHAR2(10)    可空  
    private String InsuredBankAccNo;//   被保人银行账户 String  VARCHAR2(40)    可空  
    private String InsuredDevelop;//扩展字段    String  VARCHAR2(2000)  可空  扩展字段以“|”分割，字段组合为：key,valuekey,value|key,value…

}
