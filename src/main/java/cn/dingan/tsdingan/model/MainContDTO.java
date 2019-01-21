package cn.dingan.tsdingan.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class MainContDTO {
    
    private String ApplyDate;//投保日期
    
    private String ApplyTime;//投保时间
    
    private String CvaliDate;//生效日期

    
    private String CvaliTime;//生效时间

    
    private String EndDate;//终止日期

    
    private String EndTime;//终止时间w

    
    private String CertifyNo;//单证号码

    
    private BigDecimal Prem;//保费      套餐总保费，售票系统维护里程与保费关系


    
    private BigDecimal MainAmount;//保额     套餐总保费，售票系统维护里程与保额关系


    
    
    private BigDecimal AdditionalAmount;//附加险保额      套餐总保费，售票系统维护里程与附加险保额关系


    
    private String ManageCom;//管理机构       在售票系统中维护，以保险公司提供的为准


    
    private String AgentCom;//代理机构编码     在售票系统中维护，以保险公司提供的为准


    
    private String ContplanCode; //"（试算时，可不传）新增支付方式：L-在线支付 。鼎安经纪约定如下：
//    inbankcode,S8600000|inbankaccno,43001780661059666666|inbankaccname, 建行长沙韭菜园支行（收）|paymode,L|tempfeeno, 支付流水号(对应银行转账的流水号)
//    "

    
    private String MainContDTOSerialno; //1-100 序号


}
