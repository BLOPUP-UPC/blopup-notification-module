package org.openmrs.module.blopup.notification.web.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openmrs.module.blopup.notification.api.impl.EmailProcessor;
import org.openmrs.module.blopup.notification.api.models.EmailRequest;

import static org.mockito.Mockito.verify;

//@ExtendWith(MockitoExtension.class)
class EmailControllerTest {
	
	@Mock
	private EmailProcessor emailProcessor;
	
	@InjectMocks
	private EmailController subject;

	@BeforeEach
	public void setupMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void should_passRequestToEmailProcessor() {
		// Given
		EmailRequest emailRequest = new EmailRequest();
		
		// When
		subject.onPost(emailRequest);
		
		// Then
		verify(emailProcessor).sendEmail(emailRequest);
	}
	
}
