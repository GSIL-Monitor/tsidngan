package cn.dingan.tsdingan.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cn.dingan.tsdingan.model.PayInfo;
import cn.dingan.tsdingan.model.Result;
import cn.dingan.tsdingan.service.SybPayService;
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
            sybPayService.wxPay(payInfo);
        }catch(Exception e) {
            logger.error(e.getMessage(), e);
            result.setSuccess(false);
        }
        return result;
    }
    
}
