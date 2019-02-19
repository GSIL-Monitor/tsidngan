package cn.dingan.tsdingan.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.dingan.tsdingan.contants.Contants;
import cn.dingan.tsdingan.dao.DaOrderDetailMapper;
import cn.dingan.tsdingan.dao.DaOrderMapper;
import cn.dingan.tsdingan.model.DaOrder;
import cn.dingan.tsdingan.model.DaOrderDetail;
import cn.dingan.tsdingan.model.PayInfo;
import cn.dingan.tsdingan.response.PayResponse;
import cn.dingan.tsdingan.service.InsureService;
import cn.dingan.tsdingan.socket.PayWebSocket;
import cn.dingan.tsdingan.utils.BeanUtils;
import cn.dingan.tsdingan.utils.HttpConnectionUtil;
import cn.dingan.tsdingan.utils.SybUtil;
import tk.mybatis.mapper.entity.Example;

@Component
public class SybPayService {
    
    
    @Autowired
    private DaOrderMapper daOrderMapper;
    
    @Autowired
    private DaOrderDetailMapper daOrderDetailMapper; 
    
    @Autowired
    private InsureService insureService;
    
	public PayResponse wxPay(PayInfo payInfo) throws Exception{
		HttpConnectionUtil http = new HttpConnectionUtil(Contants.SYB_APIURL+"/pay");
		http.init();
		TreeMap<String,String> params = new TreeMap<String,String>();
		params.put("cusid", Contants.SYB_CUSID);
		params.put("appid", Contants.SYB_APPID);
		params.put("version", "11");
		params.put("trxamt", String.valueOf(payInfo.getMoney()));
		params.put("reqsn", payInfo.getReqsn());
		params.put("paytype", payInfo.getPaytype());
		params.put("randomstr", String.valueOf(new Date().getTime()));
		params.put("body", "驾驶员意外伤害险");
		params.put("remark", "吉祥套餐编码");
		params.put("notify_url", Contants.notify_url);
	 
		params.put("sign", SybUtil.sign(params,Contants.SYB_APPKEY));
		byte[] bys = http.postParams(params, true);
		String result = new String(bys,"UTF-8");
		Map<String,String> map = handleResult(result);
    	if(null!=map) {
    		PayResponse payResponse = (PayResponse) BeanUtils.mapToObject(map, PayResponse.class);
    		return payResponse;
    	}
		
		return null;
		
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
	public PayResponse query(String reqsn) throws Exception{
		HttpConnectionUtil http = new HttpConnectionUtil(Contants.SYB_APIURL+"/query");
		http.init();
		TreeMap<String,String> params = new TreeMap<String,String>();
		params.put("cusid", Contants.SYB_CUSID);
		params.put("appid", Contants.SYB_APPID);
		params.put("version", "11");
		params.put("reqsn", reqsn);
		params.put("randomstr", SybUtil.getValidatecode(8));
		params.put("sign", SybUtil.sign(params,Contants.SYB_APPKEY));
		byte[] bys = http.postParams(params, true);
		String result = new String(bys,"UTF-8");
		Map<String,String> map = handleResult(result);
		
		print(map);
		
		PayResponse payResponse = (PayResponse) BeanUtils.mapToObject(map, PayResponse.class);
		 
		return payResponse;
	}
	
	
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
	
	public static void main(String [] args)  throws Exception{
	    HttpConnectionUtil http = new HttpConnectionUtil(Contants.SYB_APIURL+"/pay");
        http.init();
        TreeMap<String,String> params = new TreeMap<String,String>();
        params.put("cusid", Contants.SYB_CUSID);
        params.put("appid", Contants.SYB_APPID);
        params.put("version", "11");
        params.put("trxamt", String.valueOf("1"));
        params.put("reqsn", "0000000012343");
        params.put("paytype", "A01");
        params.put("randomstr", String.valueOf(new Date().getTime()));
        params.put("body", "驾驶员意外伤害险");
        params.put("remark", "吉祥套餐编码");
        params.put("notify_url", Contants.notify_url);
     
        params.put("sign", SybUtil.sign(params,Contants.SYB_APPKEY));
        byte[] bys = http.postParams(params, true);
        String result = new String(bys,"UTF-8");
        Map<String,String> map = handleResult(result);
        if(null!=map) {
            PayResponse payResponse = (PayResponse) BeanUtils.mapToObject(map, PayResponse.class);
            System.out.println(payResponse.getPayinfo());
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
	
	public void createOderBycallback(Map<String,String> map){
	    PayResponse payResponse = (PayResponse) BeanUtils.mapToObject(map, PayResponse.class);
	    //判断是否交易成功
	    if(null!=payResponse  && StringUtils.isNotBlank(payResponse.getTrxstatus()) && "0000".equals(payResponse.getTrxstatus())) {
	        
	        
	        //通知前端支付成功
            PayWebSocket payWebSocket = new PayWebSocket();
            try {
                payWebSocket.sendtoUser("支付成功","124");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
	        
	        String cusorderid = payResponse.getCusorderid();//交易单号
	        Example example = new Example(DaOrder.class);
	        example.createCriteria().andEqualTo("cusorderid",cusorderid).andEqualTo("isDeleted",cn.trasen.BootComm.Contants.IS_DELETED_FALSE);
	        DaOrder order = daOrderMapper.selectOneByExample(example);
	        
	        order.setStatus("1");//修改支付状态为已支付
	        daOrderMapper.updateByPrimaryKeySelective(order);
	        
	        
	        
	        
	        if(null!=order && StringUtils.isNotBlank(order.getOrderId())) {
	            Example example2 = new Example(DaOrderDetail.class);
	            example2.createCriteria().andEqualTo("orderId",order.getOrderId()).andEqualTo("isDeleted",cn.trasen.BootComm.Contants.IS_DELETED_FALSE);
	            
	            List<DaOrderDetail> detailList = daOrderDetailMapper.selectByExample(example2);
	            if(null!=detailList && detailList.size()>0) {
	                //调用保险公司接口 下单
	                insureService.finishOrderByPay(order,detailList);
	            }
	        }
	    }
	}
}
