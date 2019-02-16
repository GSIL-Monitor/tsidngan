package cn.dingan.tsdingan.service;

import cn.dingan.tsdingan.model.DriverSchool;

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
}
