package cn.dingan.tsdingan.service;

import java.util.List;

import cn.dingan.tsdingan.model.DriverSchool;
import cn.trasen.core.feature.orm.mybatis.Page;

public interface DriverSchoolService {

	
	/**
	 * 
	 * @param 免费注册
	 * @return
	 */
	String insert(DriverSchool record);
	
	/**
	 * 
	 * @param 校验登录
	 * @return
	 */
	DriverSchool checkAccount(DriverSchool record);
	
	/**
	 * 
	* @Title: getDriverList
	* @Description: 获取列表
	* @param @param page
	* @param @param record
	* @param @return    参数
	* @return List<>    返回类型
	* @throws
	* @author jyq#trasen.cn
	* @date 2019年2月17日 上午9:59:38
	 */
	List<DriverSchool> getDriverList(Page page,DriverSchool record);
	
	
	/**
	 * 
	* @Title: examine
	* @Description: 审核驾校信息
	* @param @param record
	* @param @return    参数
	* @return String    返回类型
	* @throws
	* @author jyq#trasen.cn
	* @date 2019年2月17日 上午10:04:23
	 */
	String examine(DriverSchool record);
}
