package com.webapp.utils.email;

import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Test;

import com.webapp.utils.email.EmailUtil.Mail;

public class EmailUtilsTest {

	@Test
    public void sendEmail() throws Exception {

		Mail mail = new Mail();
		mail.setHost("smtp.163.com");
		mail.setSenderMail("googlecosplay@163.com");
		mail.setSmtpPwd("");

		mail.setSmtpName("googlecosplay");
		mail.setSenderName("name");
		mail.setContent("hello");
		mail.addReceiver("king", "googlecosplay@163.com");
		mail.setSubject("subject");

		assertThat(EmailUtil.sendEmail(mail), Matchers.is(true));
    }

}
