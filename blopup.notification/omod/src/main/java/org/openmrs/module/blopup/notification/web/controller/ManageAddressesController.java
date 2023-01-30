package org.openmrs.module.blopup.notification.web.controller;

import org.openmrs.api.context.Context;
import org.openmrs.module.blopup.notification.MessagingService;
import org.openmrs.module.blopup.notification.domain.gateway.Protocol;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ManageAddressesController {
	
	@RequestMapping("/module/blopup.notification/admin/manageAddresses")
	public void populateModel(HttpServletRequest request) {
		List<Protocol> protocols = Context.getService(MessagingService.class).getProtocols();
		List<String> protocolTitles = new ArrayList<String>();
		for (Protocol p : protocols) {
			protocolTitles.add(p.getProtocolId());
		}
		request.setAttribute("protocols", protocolTitles);
	}
}
