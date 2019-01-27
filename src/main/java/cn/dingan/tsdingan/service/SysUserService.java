package cn.dingan.tsdingan.service;

import cn.dingan.tsdingan.model.SysUser;

public interface SysUserService {
	
	/**
	 * 登录校验
	 * @param userName
	 * @return
	 */
	SysUser selectByUserName(SysUser record);

}
