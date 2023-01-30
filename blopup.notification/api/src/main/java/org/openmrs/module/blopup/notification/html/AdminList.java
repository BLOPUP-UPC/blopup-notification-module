package org.openmrs.module.blopup.notification.html;

import org.openmrs.module.Extension;
import org.openmrs.module.web.extension.AdministrationSectionExt;

import java.util.HashMap;
import java.util.Map;

public class AdminList extends AdministrationSectionExt {
	
	/**
	 * @see org.openmrs.module.web.extension.AdministrationSectionExt#getMediaType()
	 */
	public Extension.MEDIA_TYPE getMediaType() {
		return Extension.MEDIA_TYPE.html;
	}
	
	@Override
	public Map<String, String> getLinks() {
		HashMap<String, String> links = new HashMap<String, String>();
		links.put("module/blopup.notification/admin/sendMessage.form", "Send a Message");
		links.put("module/blopup.notification/admin/manageAddresses.form", "Manage Your Blopup Notifications Addresses");
		links.put("module/blopup.notification/admin/manageGateways.form", "Manage Blopup Notifications Gateways");
		return links;
	}
	
	@Override
	public String getTitle() {
		return "Blopup Notification";
	}
	
}
