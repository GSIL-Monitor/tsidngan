package cn.dingan.tsdingan.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dingan.tsdingan.model.DriverSchool;
import cn.dingan.tsdingan.model.Result;
import cn.dingan.tsdingan.model.SysUser;
import cn.dingan.tsdingan.service.DriverSchoolService;
import cn.dingan.tsdingan.service.SysUserService;
import cn.dingan.tsdingan.utils.MD5Util;
import cn.dingan.tsdingan.utils.UserUtil;


@Controller
@RequestMapping("/index")
public class IndexController extends BaseController{
	
	
	private final Logger log = Logger.getLogger(this.getClass());

	static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Resource
	private SysUserService sysUserService;
	
	@Autowired
	private DriverSchoolService driverSchoolService;
	
	
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
    @RequestMapping("/loginout")
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
	@RequestMapping("/loginValid")
    @ResponseBody
    public Result loginin(@RequestBody SysUser record) {
		Result result = new Result();
		try {
			SysUser user = sysUserService.selectByUserName(record);
			if(user==null){
				result.setMessage("用户不存在");
				result.setSuccess(false);
				return result;
			}
			String password=MD5Util.md5Password(record.getPassword());
			if(password.equals(user.getPassword())){
				// 登录成功
				applyAuthority(user);
				UserUtil.setUser(user);
				// 更新该用户的最后登录时间和最后登录ip
				
				log.info("登录成功！用户：" + record.getUsername() + "，登录时间" + format.format(new Date()) + "，登录ip：");
				
				result.setMessage("登录成功");
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
		result.setMessage("登录异常");
		result.setSuccess(false);
		return result;
	}
	
	private String applyAuthority(SysUser user) {
		try {
			super.getSession().setAttribute(UserUtil.LOGIN_USER, user);
		} catch (Exception e) {
			log.info("查询用户权限异常:::"+e);
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 免费注册
	 * @param record
	 * @return
	 */
	@RequestMapping("/logon")
    @ResponseBody
    public Result logon(@RequestBody DriverSchool record) {
		Result result = new Result();
		try {
			int count = driverSchoolService.insert(record);
			if(count==1) {
				result.setMessage("注册成功");
				result.setSuccess(true);
			}else {
				result.setMessage("注册失败,请联系管理员");
				result.setSuccess(false);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result.setMessage("登录异常");
		result.setSuccess(false);
		return result;
	}
}
