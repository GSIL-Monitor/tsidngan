package cn.dingan.tsdingan.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.dingan.tsdingan.model.InsuranceMeal;
import cn.dingan.tsdingan.service.InsuranceMealService;
import cn.trasen.core.entity.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 套餐
 * @author Administrator
 *
 */
@Api(tags = "套餐Controller")
@RestController
public class InsuranceMealController {
	private static final Logger logger = LoggerFactory.getLogger(InsuranceMealController.class);
	
	@Autowired
	private InsuranceMealService insuranceMealService;
	
	/**
	 * 根据套餐类型获取套餐明细
	 * @param mealType
	 * @return
	 */
	@ApiOperation(value = "根据套餐类型获取套餐明细", notes = "根据套餐类型获取套餐明细")
    @PostMapping("/insurance/meal/{mealType}")
    public Result getInsuranceMealByType(@PathVariable String mealType) {
        Result result = new Result();
        try {
        	InsuranceMeal record = insuranceMealService.getInsuranceMealByType(mealType);
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
