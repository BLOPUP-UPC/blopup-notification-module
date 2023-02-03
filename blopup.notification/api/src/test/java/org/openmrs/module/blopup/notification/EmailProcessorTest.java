/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.blopup.notification;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openmrs.api.UserService;
import org.openmrs.module.blopup.notification.api.dao.BlopupNotificationDao;
import org.openmrs.module.blopup.notification.api.impl.EmailProcessor;
import org.openmrs.module.blopup.notification.api.models.EmailRequest;

import static org.mockito.Mockito.when;

/**
 * This is a unit test, which verifies logic in BlopupNotificationService. It doesn't extend
 * BaseModuleContextSensitiveTest, thus it is run without the in-memory DB and Spring context.
 */
public class EmailProcessorTest {
	
	@InjectMocks
	EmailProcessor emailProcessor;
	
	@Mock
	BlopupNotificationDao dao;
	
	@Mock
	UserService userService;
	
	@BeforeEach
	public void setupMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void sendEmail_shouldSendEmailIfConfigurationIsPresent() {
		//Given
		Item item = new Item();
		item.setDescription("some description");
		
		when(dao.saveItem(item)).thenReturn(item);
		
		EmailRequest emailRequest = new EmailRequest();
		//when(emailProcessor.sendEmail(emailRequest)).thenReturn(null);
		
		//When
		//emailProcessor.sendEmail(emailRequest);
		
		//Then
		//assertThat(item, hasProperty("owner", is(emailRequest)));
	}
}
