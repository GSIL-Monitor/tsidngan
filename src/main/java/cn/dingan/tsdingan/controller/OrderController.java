package cn.dingan.tsdingan.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import cn.dingan.tsdingan.model.DaOrderDetail;
import cn.dingan.tsdingan.service.OrderService;
import cn.trasen.core.entity.Result;
import cn.trasen.core.feature.orm.mybatis.Page;
import io.swagger.annotations.ApiOperation;

public class OrderController {
    
    
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    
    @Autowired
    private OrderService orderService;
    /**
     * 查询列表
     * 
     * @param page
     * @param record
     */
    @ApiOperation(value = "获取报单列表", notes = "获取报单列表")
    @PostMapping("/order/list")
    public Result getOrderList(Page page, DaOrderDetail record) {
         Result result = new Result();
         try {
             List<DaOrderDetail> list = orderService.getOrderDetailList(page, record);
             result.setObject(list);
             result.setSuccess(true);
         } catch (Exception e) {
             logger.error(e.getMessage(), e);
             result.setSuccess(false);
             result.setMessage("操作失败.");
         }
        return result;
         
    }
}
