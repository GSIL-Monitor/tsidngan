package cn.dingan.tsdingan.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * 
* @ClassName: PayInfo
* @Description: 支付信息
* @author jyq#trasen.cn
* @date 2019年1月25日 下午2:59:19
*
 */
@Getter
@Setter
//@ConfigurationProperties(prefix = "pay")
public class PayInfo {
    
    private String cusid;//商户号 实际交易的商户号
    
    private String appid;//应用id 平台分配的APPID
    
    private String reqsn;//商户交易单号 商户的交易订单号
    
    private BigDecimal money;//交易金额 单位为分
    
    private String paytype;//交易方式 A01
    
    private String randomstr;//随机字符串 商户自行生成的随机字符串
    
    private String body;//订单标题 订单商品名称，为空则以商户名作为商品名称
    
    private String remark;//备注
    
    private String sign;//签名
    
    private String notify_url;//交易结果通知地址
}
