package cn.dingan.tsdingan.service.impl;

import java.security.Security;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
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
	
	@Value("${spring.mail.password}")
	private String password;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private JavaMailSenderImpl javaMailSenderImpl;

	@Override
	public void sendEmail(EmailMessageVo messageVo) {

		boolean isHtmlEmail = Boolean.FALSE;
		if (StringUtils.isNotBlank(messageVo.getContent())) {
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
	
	/**
	 * 加密发送邮件
	 */
	public void sendEmil465(EmailMessageVo messageVo) {
		try {
			Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
			final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
			// 设置邮件会话参数
			Properties props = new Properties();
			// 邮箱的发送服务器地址
			props.setProperty("mail.smtp.host", "smtp.qq.com");
			props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
			props.setProperty("mail.smtp.socketFactory.fallback", "false");
			// 邮箱发送服务器端口,这里设置为465端口
			props.setProperty("mail.smtp.port", "465");
			props.setProperty("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.auth", "true");
			// 获取到邮箱会话,利用匿名内部类的方式,将发送者邮箱用户名和密码授权给jvm
			Session session = Session.getDefaultInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});
			// 通过会话,得到一个邮件,用于发送
			Message msg = new MimeMessage(session);
			// 设置发件人
			msg.setFrom(new InternetAddress(username));
			// 设置收件人,to为收件人,cc为抄送,bcc为密送
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(messageVo.getSendTo(), false));
			msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(messageVo.getSendTo(), false));
			msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(messageVo.getSendTo(), false));
			msg.setSubject(messageVo.getTitle());
			// 设置邮件消息
			msg.setText(messageVo.getContent());
			// 设置发送的日期
			msg.setSentDate(new Date());

			// 调用Transport的send方法去发送邮件
			Transport.send(msg);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] agrs) {

	}
}
