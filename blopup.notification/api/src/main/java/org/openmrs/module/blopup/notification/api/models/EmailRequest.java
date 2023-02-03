package org.openmrs.module.blopup.notification.api.models;

import javax.validation.constraints.NotNull;

public class EmailRequest {
	
	@NotNull(message = "From Email is mandatory")
	String fromEmail;
	
	@NotNull(message = "Full Name is mandatory")
	String fullName;
	
	String subject;
	
	@NotNull(message = "Content is mandatory")
	String content;
	
	public String getFromEmail() {
		return fromEmail;
	}
	
	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
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
