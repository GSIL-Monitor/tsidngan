package cn.dingan.tsdingan.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cn.dingan.tsdingan.model.DaInsure;
import cn.dingan.tsdingan.service.InsureService;
import cn.trasen.core.entity.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
* @ClassName: InsureController
* @Description: TODO(这里用一句话描述这个类的作用)
* @author jyq#trasen.cn
* @date 2019年2月12日 下午5:09:48
*
 */

@Api(tags = "投保接口Controller")
@RestController
public class InsureController {
    
    private Logger logger = LoggerFactory.getLogger(InsureController.class);
    
    
    @Autowired
    private InsureService insureService;
    
    /**
     * 
    * @Title: trial
    * @Description: 投保試算
    * @param @param id
    * @param @return    参数
    * @return Result    返回类型
    * @throws
    * @author jyq#trasen.cn
    * @date 2019年2月12日 下午5:11:11
     */
    @ApiOperation(value = "投保試算", notes = "投保試算")
    @PostMapping("/insure/try")
    public Result trial(@RequestBody DaInsure record) {
        try {
        	Result result  = insureService.insureTry(record);
        	return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
    
    @ApiOperation(value = "投保試算", notes = "投保試算")
    @GetMapping("/insure/try/test")
    public Result trialtest() {
        Result result = new Result();
        try {
            String record = insureService.insureTry();
            result.setObject(record);
            result.setSuccess(true);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setMessage("操作失败.");
            result.setSuccess(false);
        }
        return result;
    }
}
