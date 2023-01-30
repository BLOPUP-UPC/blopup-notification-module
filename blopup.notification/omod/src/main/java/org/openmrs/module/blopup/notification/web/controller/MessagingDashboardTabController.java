package org.openmrs.module.blopup.notification.web.controller;

import org.openmrs.Patient;
import org.openmrs.api.context.Context;
import org.openmrs.module.blopup.notification.MessagingAddressService;
import org.openmrs.module.blopup.notification.MessagingService;
import org.openmrs.web.controller.PortletController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class MessagingDashboardTabController extends PortletController {
	
	@Override
	protected void populateModel(HttpServletRequest request, Map<String, Object> model) {
		request.setAttribute("protocols", Context.getService(MessagingService.class).getProtocols());
		Patient p = (Patient) model.get("patient");
		request.setAttribute("patientAddresses", Context.getService(MessagingAddressService.class)
		        .getMessagingAddressesForPerson(p));
	}
}
