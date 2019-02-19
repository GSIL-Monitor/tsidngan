package cn.dingan.tsdingan.service.impl;

import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import cn.dingan.tsdingan.model.EmailMessageVo;
import cn.dingan.tsdingan.service.EmailService;

/**
 * @ClassName: EmailServiceImpl
 * @Description: 发送邮件
 * @author quedd#trasen.cn
 * @date 2018年7月17日 下午12:07:27
 *
 */
@Service
public class EmailServiceImpl implements EmailService {

	private org.slf4j.Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

	@Value("${spring.mail.username}")
	private String username;

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private JavaMailSenderImpl javaMailSenderImpl;

	@Override
	public void sendEmail(EmailMessageVo messageVo) {

		boolean isHtmlEmail = Boolean.FALSE;
		if(StringUtils.isNotBlank(messageVo.getContent())) {
			// 判断是否包含html元素
			isHtmlEmail = Pattern.matches("<[a-zA-Z]+.*?>([\\s\\S]*?)</[a-zA-Z]*>", messageVo.getContent());
		}
		logger.info("isHtmlEmail:{}", isHtmlEmail);
		if (Boolean.FALSE.equals(isHtmlEmail)) {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom(username);
			message.setTo(messageVo.getSendTo().split(";"));
			if (StringUtils.isNotEmpty(messageVo.getCc())) {
				message.setCc(messageVo.getCc().split(";"));
			}
			message.setSubject(messageVo.getTitle());
			message.setText(messageVo.getContent());
			mailSender.send(message);
		} else {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper;
			try {
				helper = new MimeMessageHelper(message, true);
				helper.setFrom(username);
				helper.setTo(messageVo.getSendTo().split(";"));
				if (StringUtils.isNotEmpty(messageVo.getCc())) {
					helper.setCc(messageVo.getCc().split(";"));
				}
				helper.setSubject(messageVo.getTitle());
				helper.setText(messageVo.getContent(), true);
				mailSender.send(message);
			} catch (MessagingException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}
	
	
	public void senEmailPort(EmailMessageVo messageVo) {
	    javaMailSenderImpl.setPort(465);
	    javaMailSenderImpl.setHost("smtp.exmail.qq.com");
	    javaMailSenderImpl.setUsername("portal@trasen.cn");
	    javaMailSenderImpl.setPassword("Hncx6888");
	    
	    MimeMessage message =  javaMailSenderImpl.createMimeMessage();
	    MimeMessageHelper helper;
	    try {
            helper = new MimeMessageHelper(message, true);
            helper.setTo(messageVo.getSendTo().split(";"));
            if (StringUtils.isNotEmpty(messageVo.getCc())) {
                helper.setCc(messageVo.getCc().split(";"));
            }
            helper.setSubject("测试");
            helper.setText(messageVo.getContent(), true);
            javaMailSenderImpl.send(message);
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
}
