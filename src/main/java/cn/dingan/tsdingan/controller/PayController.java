package cn.dingan.tsdingan.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cn.dingan.tsdingan.model.PayInfo;
import cn.dingan.tsdingan.model.Result;
import cn.dingan.tsdingan.response.PayResponse;
import cn.dingan.tsdingan.service.impl.SybPayService;
import io.swagger.annotations.Api;

@Api(tags = "支付")
@RestController
public class PayController {
    
    
    private static final Logger logger = LoggerFactory.getLogger(PayController.class);
    
    @Autowired
    private SybPayService sybPayService;
    
    
    @PostMapping("/pay/wechat")
    public Result valdiateMobile(@RequestBody PayInfo payInfo) {
        Result result = new Result();
        try {
        	PayResponse  payResponse= sybPayService.wxPay(payInfo);
        	if(null!=payResponse && "SUCCESS".equals(payResponse.getRetcode())) {
        		result.setMessage("唤醒支付成功");
    			result.setSuccess(true);
    			result.setObject(payResponse);
        	}else{
        		result.setMessage("操作失败,请联系管理员");
    			result.setSuccess(false);
        	}
    		 
        }catch(Exception e) {
            logger.error(e.getMessage(), e);
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }
    
    /**
     * 
    * @Title: callback
    * @Description: 通联支付完成后 回调方法
    * @param @param request
    * @param @return    参数
    * @return String    返回类型
    * @throws
    * @author jyq#trasen.cn
    * @date 2019年2月19日 下午2:00:50
     */
    @PostMapping("/pay/callback")
    public String callback(HttpServletRequest request) {
        
        Map<String, String> formData = new HashMap<>();
        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String key = (String) enu.nextElement();
            formData.put(key, request.getParameter(key).toString());
        }
        //根据回调的订单号下单保险公司
        try {
            sybPayService.createOderBycallback(formData);
            return "success";
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
      
        return "fasle";        
    }
    
    /**
     * 查询交易状态
     * @param reqsn
     * @return
     */
    @GetMapping("/pay/query/{reqsn}/")
    public Result query(@PathVariable String reqsn) {
    	Result result = new Result();
        try {
        	PayResponse payResponse = sybPayService.query(reqsn);
        	if(null!=payResponse &&  "0000".equals(payResponse.getTrxstatus())) {
        		result.setMessage("交易成功");
    			result.setSuccess(true);
        	}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			result.setMessage("查询失败,请稍后查询");
			result.setSuccess(false);
		}
        
        return result;
    }
}
