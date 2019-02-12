package cn.dingan.tsdingan.service.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.dingan.tsdingan.dao.DriverSchoolMapper;
import cn.dingan.tsdingan.dao.SysUserMapper;
import cn.dingan.tsdingan.model.DriverSchool;
import cn.dingan.tsdingan.model.EmailMessageVo;
import cn.dingan.tsdingan.model.SysUser;
import cn.dingan.tsdingan.service.DriverSchoolService;
import cn.dingan.tsdingan.service.EmailService;
import cn.dingan.tsdingan.utils.MD5Util;
import cn.trasen.BootComm.Contants;
import cn.trasen.commons.util.ApplicationUtils;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = true)
public class DriverSchoolServiceImpl implements DriverSchoolService {
	
	@Autowired
	private DriverSchoolMapper driverSchoolMapper;
	
	@Autowired
	private SysUserMapper sysUserMapper;
	
	@Autowired
	private EmailService emailService;
	
	/**
	 * 注册
	 */
	@Transactional(readOnly=false)
	public int insert(DriverSchool record){
		record.setDriverSchoolId(ApplicationUtils.GUID32());
		record.setCreateDate(new Date());
		record.setIsExamine("1");
		record.setIsDeleted(Contants.IS_DELETED_FALSE);
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
		user.setId(ApplicationUtils.GUID32());
		user.setAccount(record.getAccount());
		user.setAccountType("1");
		user.setPassword(MD5Util.md5Password("123456"));//初始密码123456
		user.setDriverSchoolId(record.getDriverSchoolId());
		user.setIsactivation("1");
		
		record.setIsDeleted(Contants.IS_DELETED_FALSE);
		
		sysUserMapper.insert(user);
		
		//发送邮件
		if(StringUtils.isNotBlank(record.getEmail())) {
		    EmailMessageVo  messageVo = new EmailMessageVo();
	        messageVo.setContent("测试------------");
	        messageVo.setSendTo(record.getEmail());
	        emailService.sendEmail(messageVo);
		}
	}
}
