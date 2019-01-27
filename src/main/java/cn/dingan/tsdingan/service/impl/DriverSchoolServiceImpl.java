package cn.dingan.tsdingan.service.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.dingan.tsdingan.dao.DriverSchoolMapper;
import cn.dingan.tsdingan.dao.SysUserMapper;
import cn.dingan.tsdingan.model.DriverSchool;
import cn.dingan.tsdingan.model.SysUser;
import cn.dingan.tsdingan.service.DriverSchoolService;
import cn.dingan.tsdingan.utils.MD5Util;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = true)
public class DriverSchoolServiceImpl implements DriverSchoolService {
	
	@Autowired
	private DriverSchoolMapper driverSchoolMapper;
	
	@Autowired
	private SysUserMapper sysUserMapper;
	
	/**
	 * 注册
	 */
	@Transactional(readOnly=false)
	public int insert(DriverSchool record){
		record.setDriverSchoolId(UUID.randomUUID().toString());
		record.setCreateDate(new Date());
		record.setIsExamine("1");
		//创建用户
		insertUser(record);
		
		return driverSchoolMapper.insert(record);
	}
	
	/**
	 * 新建驾校用户
	 * @param record
	 */
	private void insertUser(DriverSchool record) {
		SysUser user = new SysUser();
		user.setId(UUID.randomUUID().toString());
		user.setAccount(record.getAccount());
		user.setAccountType("1");
		user.setPassword(MD5Util.md5Password("123456"));//初始密码123456
		user.setDriverSchoolId(record.getDriverSchoolId());
		user.setIsactivation("1");
		sysUserMapper.insert(user);
	}
}
