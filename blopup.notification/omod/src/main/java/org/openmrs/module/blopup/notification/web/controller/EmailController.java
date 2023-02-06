/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 * <p>
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.blopup.notification.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.UserService;
import org.openmrs.module.blopup.notification.api.impl.EmailProcessor;
import org.openmrs.module.blopup.notification.api.models.EmailRequest;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.v1_0.controller.BaseRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

//ws/rest/v1/email -> GET, POST, PATCH, DELETE
@Controller
@RequestMapping(value = "/rest/" + RestConstants.VERSION_1 + "/email")
public class EmailController extends BaseRestController {
	
	/**
	 * Logger for this class and subclasses
	 */
	protected final Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EmailProcessor emailProcessor;
	
	/**
	 * @param emailRequest
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.OK)
	public String onPost(@RequestBody EmailRequest emailRequest) {
		emailProcessor.sendEmail(emailRequest);
		return "Success";
	}
	
}
