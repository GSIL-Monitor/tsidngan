package cn.dingan.tsdingan.service;

import cn.dingan.tsdingan.model.EmailMessageVo;

public interface EmailService {

	/**
	 * 
	* @Title: sendEmail
	* @Description: 发送邮件
	* @param @param messageVo    参数
	* @return void    返回类型
	* @throws
	* @author jyq#trasen.cn
	* @date 2019年2月12日 下午4:01:06
	 */
	public void sendEmail(EmailMessageVo messageVo);
}
