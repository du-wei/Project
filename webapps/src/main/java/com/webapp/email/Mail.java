package com.webapp.email;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: Mail.java
 * @Package com.webapp.email
 * @Description: TODO 类型描述
 * @author king chenglong@coweibo.cn
 * @date 2012-12-14 下午2:06:43
 * @version V1.0
 */
public class Mail {

	/** @Fields host : HOST */
	private String host;
	/** @Fields port : Port */
	private int port;
	/** @Fields connectionTimeout : 连接超时时间 */
	private int connectionTimeout;
	/** @Fields smtpUser : 发送邮件帐号 */
	private String smtpUser;
	/** @Fields smtpPassword : 发送邮件密码 */
	private String smtpPassword;
	/** @Fields senderMail : 显示的发件人地址 */
	private String senderMail;
	/** @Fields senderName :显示的发件人 名称 */
	private String senderName;
	/** @Fields receiverMail : 收件人（显示名称和邮件地址） */
	private Map<String, String> receiverMail;
	/** @Fields subject : 邮件主题 */
	private String subject;
	/** @Fields contentType : 邮件类型 */
	private String contentType;
	/** @Fields charSet : 邮件编码格式 */
	private String charSet;
	/** @Fields content : 邮件内容 */
	private String content;
	/** @Fields contentTemplate : 邮件内容模版 */
	// private String contentTemplate;

	/** @Fields attachment : 附件（名字和内容） */
	private Map<String, String> attachment;

	public Mail() {
	}

	public void clearReceiver() {
		if (receiverMail != null)
			receiverMail.clear();
	}

	public Map<String, String> addReceiver(String name, String address) {
		if (receiverMail == null)
			receiverMail = new HashMap<String, String>();
		receiverMail.put(name, address);
		return this.receiverMail;
	}

	public Map<String, String> addReceiver(String[] address) {
		for (int i = 0; i < address.length; i++) {
			addReceiver(address[i].substring(0, address[i].indexOf("@")),
					address[i]);
		}
		return this.receiverMail;
	}

	public Map<String, String> addReceiver(List<String> address) {
		return addReceiver(address.toArray(new String[] {}));
	}

	public Map<String, String> addReceiver(Map<String, String> address) {
		Iterator<String> iterator = address.keySet().iterator();
		for (; iterator.hasNext();) {
			String key = iterator.next();
			addReceiver(key, address.get(key));
		}
		return this.receiverMail;
	}

	public Map<String, String> addAttachment(String name, String path) {
		if (attachment == null)
			attachment = new HashMap<String, String>();
		attachment.put(name, path);
		return this.attachment;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getConnectionTimeout() {
		return connectionTimeout;
	}

	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	public String getSmtpUser() {
		return smtpUser;
	}

	public void setSmtpUser(String smtpUser) {
		this.smtpUser = smtpUser;
	}

	public String getSmtpPassword() {
		return smtpPassword;
	}

	public void setSmtpPassword(String smtpPassword) {
		this.smtpPassword = smtpPassword;
	}

	public String getSenderMail() {
		return senderMail;
	}

	public void setSenderMail(String senderMail) {
		this.senderMail = senderMail;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public Map<String, String> getReceiverMail() {
		return receiverMail;
	}

	public void setReceiverMail(Map<String, String> receiverMail) {
		this.receiverMail = receiverMail;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getCharSet() {
		return charSet;
	}

	public void setCharSet(String charSet) {
		this.charSet = charSet;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Map<String, String> getAttachment() {
		return attachment;
	}

	public void setAttachment(Map<String, String> attachment) {
		this.attachment = attachment;
	}

}
