package cn.dingan.tsdingan.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.dingan.tsdingan.model.DriverSchool;
import cn.dingan.tsdingan.model.EmailMessageVo;
import cn.dingan.tsdingan.model.Result;
import cn.dingan.tsdingan.service.DriverSchoolService;
import cn.dingan.tsdingan.service.EmailService;
import cn.dingan.tsdingan.service.SysUserService;
import cn.dingan.tsdingan.utils.MD5Util;
import cn.dingan.tsdingan.utils.TokenUtils;
import cn.dingan.tsdingan.utils.UserUtil;


@RestController
public class IndexController extends BaseController{
	
	
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Resource
	private SysUserService sysUserService;
	
	@Autowired
	private DriverSchoolService driverSchoolService;
	
	@Autowired
    private EmailService emailService;
	
	
	@RequestMapping("/topwd")
    public String topwd() {
        return "password";
    }
	
//	@RequestMapping("/changepwd")
//    @ResponseBody
//    public Object changepwd(@RequestParam("oldpwd") String oldpwd,
//                    @RequestParam("newpwd") String newpwd) {
//        SysUser user = UserUtil.getUser();
//        String pass = MD5Util.md5Password(oldpwd);
//        if (pass.equals(user.getPwd())) {
//            String newpass = MD5Util.md5Password(newpwd);
//            user.setPwd(newpass);
//            sysUserService.insert(user);
//            super.getSession().setAttribute(UserUtil.LOGIN_USER, user);
//            return new Result(true,"修改成功");
//        }
//        return new Result(false, "原密码输入错误");
//    }
	
	
	/**
     * 
     * @Description: 退出登陆
     * @param @param model
     * @param @return
     * @return String
     * @throws @author penghb <penghongbao@liangyibang.com> 2016-6-12 上午11:48:52
     */
    @RequestMapping("/index/loginout")
    public String logout(Model model) {
        super.getSession().removeAttribute(UserUtil.LOGIN_USER);
        super.getSession().removeAttribute(UserUtil.RESOURCES);
        return "login";
    }

	/**
	 * 登录校验
	 * @param loginName
	 * @param passWord
	 * @param model
	 * @return
	 */
	@RequestMapping("/index/loginValid")
    @ResponseBody
    public Result loginValid(@RequestBody DriverSchool record) {
		Result result = new Result();
		try {
			DriverSchool user = driverSchoolService.checkAccount(record);
			if(user==null){
				result.setMessage("用户不存在");
				result.setSuccess(false);
				return result;
			}
			String password=MD5Util.md5Password(record.getPassword());
			if(password.equals(user.getPassword())){
				// 登录成功
				applyAuthority(user);
				String token = TokenUtils.createToken(user.getAccount());
				user.setToken(token);
			
				UserUtil.setUser(user);
				// 更新该用户的最后登录时间和最后登录ip
				
				logger.info("登录成功！用户：" + record.getAccount() + "，登录时间" + format.format(new Date()) + "，登录ip：");
				
				result.setMessage("登录成功");
				result.setObject(user);
				result.setSuccess(true);
				
				return result;
			}else{
				result.setMessage("账号或者密码错误");
				result.setSuccess(false);
				return result;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	private String applyAuthority(DriverSchool user) {
		try {
			super.getSession().setAttribute(UserUtil.LOGIN_USER, user);
		} catch (Exception e) {
			logger.error("查询用户权限异常:::"+e);
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 免费注册
	 * @param record
	 * @return
	 */
	@RequestMapping("/index/logon")
    @ResponseBody
    public Result logon(@RequestBody DriverSchool record) {
		Result result = new Result();
		try {
			String message = driverSchoolService.insert(record);
			if(StringUtils.isNotBlank(message)) {
				result.setMessage(message);
				result.setSuccess(true);
			}else {
				result.setMessage("注册失败,请联系管理员");
				result.setSuccess(false);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping("/index/test")
    public String test(Model model) {
	    //发送邮件
	    EmailMessageVo  messageVo = new EmailMessageVo();
	    messageVo.setContent("测试------------");
	    messageVo.setSendTo("380053453@qq.com");
	    emailService.sendEmail(messageVo);
	    
        return "true";
    }
}
