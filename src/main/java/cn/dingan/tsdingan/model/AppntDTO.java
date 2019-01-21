package cn.dingan.tsdingan.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
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

}
