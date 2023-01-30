/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 * <p>
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.blopup.notification;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.BaseModuleActivator;
import org.openmrs.scheduler.SchedulerException;
import org.openmrs.scheduler.TaskDefinition;
import org.openmrs.util.OpenmrsConstants;

import java.util.UUID;

/**
 * This class contains the logic that is run every time this module is either started or shutdown
 */
public class BlopupNotificationActivator extends BaseModuleActivator {
	
	private Log log = LogFactory.getLog(this.getClass());
	
	private static String TASK_NAME = "Blopup Notification Module Gateway Manager";
	
	/**
	 * A boolean used to protect against multiple started() calls
	 */
	private boolean startedCalled = false;
	
	public void started() {
		log.info("Started Blopup Notification Module");
		if (!startedCalled)
			createGatewayManagerTask();
	}
	
	public void willStop() {
		log.info("Shutting down Blopup Notification Module");
	}
	
	/**
	 * This method creates the task that polls the database and dispatches outgoing messages
	 */
	private void createGatewayManagerTask() {
		//temporarily add the privilege to manage the scheduler
		Context.addProxyPrivilege(OpenmrsConstants.PRIV_MANAGE_SCHEDULER);
		TaskDefinition dispatchMessagesTaskDef = Context.getSchedulerService().getTaskByName(TASK_NAME);
		
		if (dispatchMessagesTaskDef == null) {
			dispatchMessagesTaskDef = new TaskDefinition();
			dispatchMessagesTaskDef.setUuid(UUID.randomUUID().toString());
			dispatchMessagesTaskDef.setName(TASK_NAME);
			dispatchMessagesTaskDef.setDescription("Handles the dispatching of messages for the Messaging Module.");
			dispatchMessagesTaskDef.setStartOnStartup(true);
			dispatchMessagesTaskDef.setStartTime(null);
			dispatchMessagesTaskDef.setRepeatInterval(5L);
			dispatchMessagesTaskDef
			        .setTaskClass("org.openmrs.module.blopup.notification.schedulertask.DispatchMessagesTask");
			try {
				Context.getSchedulerService().scheduleTask(dispatchMessagesTaskDef);
			}
			catch (SchedulerException e) {
				log.error("Error creating the gateway manager task in the scheduler", e);
			}
		}
		startedCalled = true;
	}
	
}
