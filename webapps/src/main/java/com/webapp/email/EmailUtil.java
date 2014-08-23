package com.webapp.email;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;

import javax.mail.internet.MimeUtility;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;
import org.apache.log4j.Logger;

/**
 * @ClassName: EmailUtil.java
 * @Package com.webapp.email
 * @Description: TODO 类型描述
 * @author king king
 * @date 2013年12月20日 下午6:45:08
 * @version V1.0
 */
public class EmailUtil {

	private static Logger log = Logger.getLogger(EmailUtil.class.getName());
	public static final String TEXT_PLAIN = "1";
	public static final String TEXT_HTML = "2";
	public static final String ATTACHMENT_PLAIN = "3";

	public static boolean sendEmail(Mail mail) {
		try {
			if (mail == null)
				throw new NullPointerException("邮件参数不能为空。");
			String type = mail.getContentType() == null ? EmailUtil.TEXT_HTML
					: mail.getContentType();

			Email email = null;
			if (type.equals(EmailUtil.TEXT_PLAIN)) {
				email = new SimpleEmail();
			} else if (type.equals(EmailUtil.TEXT_HTML)) {
				email = new HtmlEmail();
			} else if (type.equals(EmailUtil.ATTACHMENT_PLAIN)) {
				email = listAttachment(mail);
			}
			email.setSocketTimeout(mail.getConnectionTimeout());
			email.setHostName(mail.getHost());
			if (mail.getPort() != 0) {
				email.setSmtpPort(mail.getPort());
			}
			email.setAuthentication(mail.getSmtpUser(), mail.getSmtpPassword());
			Map<String, String> receivers = mail.getReceiverMail();
			for (String recvName : receivers.keySet()) {
				email.addTo(receivers.get(recvName), recvName);
			}
			email.setFrom(mail.getSenderMail(), mail.getSenderName());
			email.setSubject(mail.getSubject());
			email.setMsg(mail.getContent());
			String charset = mail.getCharSet();
			email.setCharset((charset == null || charset.equals("")) ? "utf-8"
					: charset);

			email.send();
		} catch (Exception e) {
			log.error("发送邮件失败。", e);
			return false;
		}
		return true;
	}

	/**
	 * @Title: listAttachment
	 * @Description: TODO 方法描述
	 * @param @param mail
	 * @param @throws UnsupportedEncodingException
	 * @param @throws EmailException 参数描述
	 * @return void 返回类型描述
	 * @throws
	 */
	private static Email listAttachment(Mail mail)
			throws UnsupportedEncodingException, EmailException {
		Email email = new MultiPartEmail();
		if (mail.getAttachment() != null) {
			Map<String, String> map = mail.getAttachment();
			Iterator<String> keys = map.keySet().iterator();
			while (keys.hasNext()) {
				String key = keys.next();
				EmailAttachment attachment = new EmailAttachment();
				attachment.setDisposition(EmailAttachment.ATTACHMENT);
				attachment.setName(MimeUtility.encodeText(key));
				attachment.setPath(map.get(key).toString());
				((MultiPartEmail) email).attach(attachment);
			}
		}
		return email;
	}

	public static void main(String[] args) {
		Mail mail = new Mail();

		mail.setHost("smtp.163.com");
		mail.setSenderMail("googlecosplay@163.com");
		mail.setSmtpUser("googlecosplay");
		mail.setSmtpPassword("329852101xin");

		mail.addReceiver("chenglong", "googlecosplay@163.com");
		mail.setSenderName("name");
		mail.setContent("what");
		mail.setSubject("subject");

		EmailUtil.sendEmail(mail);
	}
}
