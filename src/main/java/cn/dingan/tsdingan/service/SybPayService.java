package cn.dingan.tsdingan.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;


import cn.dingan.tsdingan.contants.Contants;
import cn.dingan.tsdingan.model.PayInfo;
import cn.dingan.tsdingan.utils.HttpConnectionUtil;
import cn.dingan.tsdingan.utils.SybUtil;

public class SybPayService {
    
    
	public Map<String,String> wxPay(PayInfo payInfo) throws Exception{
		HttpConnectionUtil http = new HttpConnectionUtil(Contants.SYB_APIURL+"/pay");
		http.init();
		TreeMap<String,String> params = new TreeMap<String,String>();
		params.put("cusid", Contants.SYB_CUSID);
		params.put("appid", Contants.SYB_APPID);
		params.put("version", "11");
		params.put("trxamt", String.valueOf(payInfo.getTrxamt()));
		params.put("reqsn", payInfo.getReqsn());
		params.put("paytype", Contants.PAY_TYPE_WX);
		params.put("randomstr", String.valueOf(new Date().getTime()));
		params.put("body", "驾驶员意外伤害险");
		params.put("remark", "吉祥套餐编码");
		params.put("notify_url", Contants.notify_url);
	 
		params.put("sign", SybUtil.sign(params,Contants.SYB_APPKEY));
		byte[] bys = http.postParams(params, true);
		String result = new String(bys,"UTF-8");
		Map<String,String> map = handleResult(result);
		return map;
		
	}
	
//	public Map<String,String> cancel(long trxamt,String reqsn,String oldtrxid,String oldreqsn) throws Exception{
//		HttpConnectionUtil http = new HttpConnectionUtil(Contants.SYB_APIURL+"/cancel");
//		http.init();
//		TreeMap<String,String> params = new TreeMap<String,String>();
	
//		params.put("cusid", Contants.SYB_CUSID);
//		params.put("appid", Contants.SYB_APPID);
//		params.put("version", "11");
//		params.put("trxamt", String.valueOf(trxamt));
//		params.put("reqsn", reqsn);
//		params.put("oldtrxid", oldtrxid);
//		params.put("oldreqsn", oldreqsn);
//		params.put("randomstr", SybUtil.getValidatecode(8));
//		params.put("sign", SybUtil.sign(params,Contants.SYB_APPKEY));
//		byte[] bys = http.postParams(params, true);
//		String result = new String(bys,"UTF-8");
//		Map<String,String> map = handleResult(result);
//		return map;
//	}
//	
//	public Map<String,String> refund(long trxamt,String reqsn,String oldtrxid,String oldreqsn) throws Exception{
//		HttpConnectionUtil http = new HttpConnectionUtil(Contants.SYB_APIURL+"/refund");
//		http.init();
//		TreeMap<String,String> params = new TreeMap<String,String>();
//		params.put("cusid", Contants.SYB_CUSID);
//		params.put("appid", Contants.SYB_APPID);
//		params.put("version", "11");
//		params.put("trxamt", String.valueOf(trxamt));
//		params.put("reqsn", reqsn);
//		params.put("oldreqsn", oldreqsn);
//		params.put("oldtrxid", oldtrxid);
//		params.put("randomstr", SybUtil.getValidatecode(8));
//		params.put("sign", SybUtil.sign(params,Contants.SYB_APPKEY));
//		byte[] bys = http.postParams(params, true);
//		String result = new String(bys,"UTF-8");
//		Map<String,String> map = handleResult(result);
//		return map;
//	}
//	
//	public Map<String,String> query(String reqsn,String trxid) throws Exception{
//		HttpConnectionUtil http = new HttpConnectionUtil(Contants.SYB_APIURL+"/query");
//		http.init();
//		TreeMap<String,String> params = new TreeMap<String,String>();
//		params.put("cusid", Contants.SYB_CUSID);
//		params.put("appid", Contants.SYB_APPID);
//		params.put("version", "11");
//		params.put("reqsn", reqsn);
//		params.put("trxid", trxid);
//		params.put("randomstr", SybUtil.getValidatecode(8));
//		params.put("sign", SybUtil.sign(params,Contants.SYB_APPKEY));
//		byte[] bys = http.postParams(params, true);
//		String result = new String(bys,"UTF-8");
//		Map<String,String> map = handleResult(result);
//		return map;
//	}
	
	
	@SuppressWarnings({"unchecked", "rawtypes"})
    public static Map<String,String> handleResult(String result) throws Exception{
		Map map = SybUtil.json2Obj(result, Map.class);
		if(map == null){
			throw new Exception("返回数据错误");
		}
		if("SUCCESS".equals(map.get("retcode"))){
			TreeMap tmap = new TreeMap();
			tmap.putAll(map);
			String sign = tmap.remove("sign").toString();
			String sign1 = SybUtil.sign(tmap,Contants.SYB_APPKEY);
			if(sign1.toLowerCase().equals(sign.toLowerCase())){
				return map;
			}else{
				throw new Exception("验证签名失败");
			}
			
		}else{
			throw new Exception(map.get("retmsg").toString());
		}
	}
	
	public static void main(String [] args) {
	    SybPayService service = new SybPayService();
	    PayInfo payInfo = new PayInfo();
	    payInfo.setTrxamt(new BigDecimal(1000));
	    payInfo.setReqsn("123456");
	    try {
            Map<String,String> map = service.wxPay(payInfo);
            print(map);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	
	public static void print(Map<String, String> map){
        System.out.println("返回数据如下:");
        if(map!=null){
            for(String key:map.keySet()){
                System.out.println(key+":"+map.get(key));
            }
        }
    }
}
