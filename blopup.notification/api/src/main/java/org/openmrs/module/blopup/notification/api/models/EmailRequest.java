package org.openmrs.module.blopup.notification.api.models;

import javax.validation.constraints.NotNull;

public class EmailRequest {

	String subject;
	
	@NotNull(message = "Content is mandatory")
	String content;

	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
}
