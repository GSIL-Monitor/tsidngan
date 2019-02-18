package cn.dingan.tsdingan.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.dingan.tsdingan.dao.DriverSchoolMapper;
import cn.dingan.tsdingan.dao.SerialnoMapper;
import cn.dingan.tsdingan.model.DriverSchool;
import cn.dingan.tsdingan.model.EmailMessageVo;
import cn.dingan.tsdingan.model.Serialno;
import cn.dingan.tsdingan.service.DriverSchoolService;
import cn.dingan.tsdingan.service.EmailService;
import cn.dingan.tsdingan.utils.MD5Util;
import cn.trasen.BootComm.Contants;
import cn.trasen.commons.util.ApplicationUtils;
import cn.trasen.core.feature.orm.mybatis.Page;
import tk.mybatis.mapper.entity.Example;

@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = true)
public class DriverSchoolServiceImpl implements DriverSchoolService {
	
	@Autowired
	private DriverSchoolMapper driverSchoolMapper;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private SerialnoMapper serialnoMapper;
	
	/**
	 * 注册
	 */
	@Transactional(readOnly=false)
	public String insert(DriverSchool record){
		record.setDriverSchoolId(ApplicationUtils.GUID32());
		record.setCreateDate(new Date());
		record.setIsExamine("1");
		record.setIsDeleted(Contants.IS_DELETED_FALSE);
//		//创建用户
//		String msg  = insertUser(record);
		
		String account = getAccount(record.getProvince());
		record.setAccount(account);
		String password=MD5Util.md5Password("123456");
		record.setPassword(password);
		record.setAccountType("2");
		driverSchoolMapper.insert(record);
		/**
		 * 发送邮件
		 */
		String msg = sendEmail(record);
		
		return msg;
	}
	
	/**
	 * 
	 * @param 登录校验
	 * @return
	 */
	public DriverSchool checkAccount(DriverSchool record) {
		
		if(StringUtils.isNotBlank(record.getAccount())) {
			Example example = new Example(DriverSchool.class);
			example.createCriteria().andEqualTo("account",record.getAccount());
			
			return driverSchoolMapper.selectOneByExample(example);
		}
		return null;
		
	}
	
	 
	/**
	 * 发送邮件
	 * @param record
	 * @return 
	 */
	private String sendEmail(DriverSchool record) {
		if(StringUtils.isNotBlank(record.getEmail())) {
		    EmailMessageVo  messageVo = new EmailMessageVo();
//	        messageVo.setContent("感谢您注册鼎安保险，您的账号信息为"+user.getAccount()+",您的密码为123456,我们已经发送了一封激活邮件到您的邮箱"+record.getEmail()+"");
	        messageVo.setContent("感谢您注册，您的账号信息为"+record.getAccount()+",您的密码为123456,我们已经发送了一封激活邮件到您的邮箱"+record.getEmail()+"");
	        messageVo.setSendTo(record.getEmail());
	        emailService.sendEmail(messageVo);
	        return "您的账号信息为"+record.getAccount()+",您的密码为123456,我们已经发送了一封激活邮件到您的邮箱"+record.getEmail()+"";
		}
		return null;
	}
	
	/**
	 * 
	 * @param 根据省份设置账号
	 * @return
	 */
	private String getAccount(String province) {
		Example example = new Example(Serialno.class);
		example.createCriteria().andEqualTo("province",province).andEqualTo("isDeleted",Contants.IS_DELETED_FALSE).andEqualTo("type",2);
		List<Serialno> list = serialnoMapper.selectByExample(example);
		if(null!=list && list.size()>0) {
			Serialno serialno = list.get(0);
			int no = serialno.getSerialno();
			int nextNo = no+1;
			serialno.setSerialno(nextNo);
			serialnoMapper.updateByPrimaryKey(serialno);
			return province+nextNo+"";
			
		}else {
			Serialno vo = new Serialno();
			vo.setId(ApplicationUtils.GUID32());
			vo.setCreateDate(new Date());
			vo.setProvince(province);
			vo.setType("2");
			vo.setIsDeleted("N");
			vo.setSerialno(1);
			serialnoMapper.insert(vo);
			return province+"001";
		}
	}
	
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
    public List<DriverSchool> getDriverList(Page page,DriverSchool record) {
        Example example = new Example(DriverSchool.class);
        example.createCriteria().andEqualTo("isDeleted",Contants.IS_DELETED_FALSE);
        if(StringUtils.isNotBlank(record.getIsExamine())) {
            example.and().andEqualTo("isExamine",record.getIsExamine());
        }
        
        return driverSchoolMapper.selectByExample(example);
    }
    
    
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
    
    @Transactional(readOnly=false)
    public String examine(DriverSchool record) {
        
        record.setUpdateDate(new Date());
        
        driverSchoolMapper.updateByPrimaryKeySelective(record);
        return "";
    }
}
