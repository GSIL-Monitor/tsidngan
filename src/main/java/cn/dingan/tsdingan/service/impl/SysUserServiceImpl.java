package cn.dingan.tsdingan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.dingan.tsdingan.dao.SysUserMapper;
import cn.dingan.tsdingan.model.SysUser;
import cn.dingan.tsdingan.service.SysUserService;
import tk.mybatis.mapper.entity.Example;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = true)
public class SysUserServiceImpl implements SysUserService{

	@Autowired
	private SysUserMapper SysUserMapper;

	@Override
	public SysUser selectByUserName(SysUser record) {
		Example example = new Example(SysUser.class);
		example.createCriteria().andEqualTo("username",record.getUsername());
		
		return SysUserMapper.selectOneByExample(example);
	}
}
