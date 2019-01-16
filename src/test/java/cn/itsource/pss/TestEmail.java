package cn.itsource.pss;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestEmail {
	
	@Autowired
	MailSender mailSender;

	@Test
	public void testName() throws Exception {

//JavaMailSenderImpl xxx = (JavaMailSenderImpl)mailSender
		
		for (int i = 0; i < 20; i++) {
			// 简单邮件对象
			SimpleMailMessage msg = new SimpleMailMessage();
			// 发送人:和配置一致
			msg.setFrom("358669137@qq.com");
			// 收件人
			msg.setTo("sunfulingq@163.com");
			// 主题
			msg.setSubject("牛皮大学录取通知书");
			// 内容
			msg.setText("你已经被<h3>录取</h3>了");
			// 发送
			mailSender.send(msg);
			
		}

	}
}
