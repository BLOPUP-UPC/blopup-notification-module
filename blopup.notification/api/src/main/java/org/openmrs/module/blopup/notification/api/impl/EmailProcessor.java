package org.openmrs.module.blopup.notification.api.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.AdministrationService;
import org.openmrs.api.context.Context;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.blopup.notification.api.models.EmailRequest;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Component("blopup.notification.EmailProcessor")
public class EmailProcessor extends BaseOpenmrsService {
	
	protected Log log = LogFactory.getLog(this.getClass());
	
	private Session session = null;
	
	private Properties configuration = null;
	
	/**
	 * Returns the email session
	 */
	
	public Session getSession() {
		if (session == null) {
			
			AdministrationService as = Context.getAdministrationService();
			
			configuration = new Properties();
			configuration.put("mail.transport.protocol", as.getGlobalProperty("mail.transport_protocol", "smtp"));
			configuration.put("mail.smtp.host", as.getGlobalProperty("mail.smtp_host", "localhost"));
			configuration.put("mail.smtp.port", as.getGlobalProperty("mail.smtp_port", "25")); // mail.smtp_port
			configuration.put("mail.smtp.auth", as.getGlobalProperty("mail.smtp_auth", "false")); // mail.smtp_auth
			configuration.put("mail.smtp.starttls.enable", as.getGlobalProperty("mail.smtp.starttls.enable", "false"));
			configuration.put("mail.smtp.ssl.enable", as.getGlobalProperty("mail.smtp.starttls.enable", "false"));
			configuration.put("mail.debug", as.getGlobalProperty("mail.debug", "false"));
			configuration.put("mail.from", as.getGlobalProperty("mail.from", ""));
			
			final String user = as.getGlobalProperty("mail.user", "");
			final String password = as.getGlobalProperty("mail.password", "");
			
			if (StringUtils.isNotBlank(user) && StringUtils.isNotBlank(password)) {
				session = Session.getInstance(configuration, new Authenticator() {
					
					public PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(user, password);
					}
				});
			} else {
				session = Session.getInstance(configuration);
			}
		}
		return session;
	}
	
	/**
     *
     */
	public List<String> getConfigurationPropertyNames() {
		List<String> ret = new ArrayList<String>();
		ret.add("from");
		ret.add("to");
		ret.add("subject");
		ret.add("content");
		ret.add("addOutputToContent");
		ret.add("addOutputAsAttachment");
		ret.add("attachmentName");
		return ret;
	}
	
	/**
	 * Performs some action on the given emailRequest
	 * 
	 * @param emailRequest the Report to process
	 */
	public void sendEmail(EmailRequest emailRequest) {
		
		try {
			Message m = new MimeMessage(getSession());
			
			m.setFrom(new InternetAddress(configuration.getProperty("mail.from")));
			m.addRecipient(RecipientType.TO, new InternetAddress(configuration.getProperty("mail.from")));
			
			m.setSubject(StringUtils.isNotBlank(emailRequest.getSubject()) ? emailRequest.getSubject() : configuration
			        .getProperty("subject"));
			
			Multipart multipart = new MimeMultipart();
			
			MimeBodyPart contentBodyPart = new MimeBodyPart();
			if (StringUtils.isNotBlank(emailRequest.getContent())) {
				contentBodyPart.setContent(emailRequest.getContent(), "text/html");
			}
			
			multipart.addBodyPart(contentBodyPart);
			m.setContent(multipart);
			m.setText(emailRequest.getContent());
			
			Transport.send(m);
		}
		catch (Exception e) {
			throw new RuntimeException("Error occurred while sending emailRequest over email", e);
		}
	}
}
