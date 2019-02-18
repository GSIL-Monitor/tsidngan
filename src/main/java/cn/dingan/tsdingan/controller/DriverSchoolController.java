package cn.dingan.tsdingan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cn.dingan.tsdingan.model.DriverSchool;
import cn.dingan.tsdingan.service.DriverSchoolService;
import cn.trasen.BootComm.model.DataSet;
import cn.trasen.core.entity.Result;
import cn.trasen.core.feature.orm.mybatis.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(tags = "投保人信息Controller")
@RestController
public class DriverSchoolController {
    
    
    @Autowired
    private DriverSchoolService driverSchoolService;
    
    /**
     * 
    * @Title: getPostList
    * @Description: 获取驾校列表
    * @param @param page
    * @param @param record
    * @param @return    参数
    * @return Result    返回类型
    * @throws
    * @author jyq#trasen.cn
    * @date 2019年2月17日 上午9:58:15
     */
    @ApiOperation(value = "获取驾校列表", notes = "获取驾校列表")
    @PostMapping("/driver/list")
    public Result getDriverList(@RequestBody DriverSchool record) {
         Result result = new Result();
         try {
        	 Page page = new Page();
        	 page.setPageSize(100);
        	 
             List<DriverSchool> list = driverSchoolService.getDriverList(page, record);
             DataSet<DriverSchool> dataset = new DataSet<>(page.getPageNo(), page.getPageSize(), page.getTotalPages(),
                     page.getTotalCount(), list);
             result.setObject(dataset);
             result.setSuccess(true);
         } catch (Exception e) {
             result.setSuccess(false);
             result.setMessage("操作失败.");
         }
        return result;
         
    }
    
    /**
     * 
    * @Title: examine
    * @Description: 审核驾校
    * @param @param record
    * @param @return    参数
    * @return Result    返回类型
    * @throws
    * @author jyq#trasen.cn
    * @date 2019年2月17日 上午10:03:49
     */
    @ApiOperation(value = "审核驾校", notes = "审核驾校")
    @PostMapping("/driver/examine")
    public Result examine(@RequestBody DriverSchool record) {
        Result result = new Result();
        try {
            driverSchoolService.examine(record);
            result.setSuccess(true);
            result.setMessage("审核成功");
        } catch (Exception e) {
            result.setMessage("添加失败,请联系管理员");
            result.setSuccess(false);
        }
        return result;
    }

}
