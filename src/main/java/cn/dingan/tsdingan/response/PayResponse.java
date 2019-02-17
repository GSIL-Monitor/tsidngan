package cn.dingan.tsdingan.response;

import lombok.Getter;
import lombok.Setter;

/**
 * 支付返回
 * @author Administrator
 *
 */
@Getter
@Setter
public class PayResponse {
	
	private String retcode;
	
	private String retmsg;
	
	private String cusid;//实际交易的商户号
	
	private String appid;//平台分配的APPID
	
	private String trxid;//交易单号 收银宝平台的交易流水号
	
	private String chnltrxid;//渠道平台交易单号 例如微信,支付宝平台的交易单号
	
	private String reqsn;//商户交易单号
	
	private String randomstr;
	
	private String trxstatus;//交易状态
	
	private String payinfo;
	
	private String sign;
	
	private String acct;//支付平台用户标识;JS支付时使用 微信支付-用户的微信openid 支付宝支付-用户user_id 微信小程序-用户小程序的openid
	
	private String trxcode;
	
	private String trxdate;
	
	private String cusorderid;//交易请求的流水号 和商户交易单号一样 只是支付完成后回调没有 reqsn字段
}
