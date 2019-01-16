package cn.dingan.tsdingan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.dingan.tsdingan.model.DataSet;
import cn.dingan.tsdingan.model.HrEmployee;
import cn.dingan.tsdingan.model.Page;
import cn.dingan.tsdingan.service.HrEmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @ClassName: EmployeeController
 * @Description: 员工基础信息
 * @author jyq#trasen.cn
 * @date 2018年4月11日 上午11:42:47
 *
 */
@Api(tags = "员工基础信息Controller")
@RestController
public class EmployeeController {


	@Autowired
	private HrEmployeeService employeeService;

           
	/**
	 * 查询列表
	 * 
	 * @param page
	 * @param record
	 */
	@ApiOperation(value = "获取员工基础信息", notes = "员工基础信息列表")
	@GetMapping("/employee/list")
	public DataSet<HrEmployee> getEmployeeList(Page page, HrEmployee record) {
		return employeeService.getDataSetList(page, record);
	}

}
