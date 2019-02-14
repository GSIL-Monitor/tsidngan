package cn.dingan.tsdingan.service;

import cn.dingan.tsdingan.model.SysUser;

public interface SysUserService {
	
	/**
	 * 登录校验
	 * @param userName
	 * @return
	 */
	SysUser selectByUserName(SysUser record);
	
	/**
	 * 
	* @Title: update
	* @Description: 修改用户信息
	* @param @param record
	* @param @return    参数
	* @return int    返回类型
	* @throws
	* @author jyq#trasen.cn
	* @date 2019年2月14日 下午4:52:10
	 */
	int update(SysUser record);

}
