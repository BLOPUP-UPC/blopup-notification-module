package org.openmrs.module.blopup.notification.db;

import org.openmrs.Person;
import org.openmrs.module.blopup.notification.MessagingAddressService;
import org.openmrs.module.blopup.notification.domain.MessagingAddress;
import org.openmrs.module.blopup.notification.domain.gateway.Protocol;

import java.util.List;

public interface MessagingAddressDAO {
	
	/**
	 * @see MessagingAddressService#getAllMessagingAddresses()
	 */
	public List<MessagingAddress> getAllMessagingAddresses();
	
	/**
	 * @see MessagingAddressService#getMessagingAddress(Integer)
	 */
	public MessagingAddress getMessagingAddress(Integer addressId);
	
	/**
	 * @see MessagingAddressService#getPreferredMessagingAddressForPerson(Person)
	 */
	public MessagingAddress getPreferredMessagingAddressForPerson(Person person);
	
	/**
	 * @see MessagingAddressService#saveMessagingAddress(MessagingAddress)
	 */
	public void saveMessagingAddress(MessagingAddress address);
	
	/**
	 * @see MessagingAddressService#deleteMessagingAddress(MessagingAddress)
	 */
	public void deleteMessagingAddress(MessagingAddress address);
	
	/**
	 * @see MessagingAddressService#retireMessagingAddress(MessagingAddress, String)
	 */
	public void voidMessagingAddress(MessagingAddress address, String reason);
	
	/**
	 * @see MessagingAddressService#unretireMessagingAddress(MessagingAddress)
	 */
	public void unvoidMessagingAddress(MessagingAddress address);
	
	public Person getPersonForAddress(String address);
	
	public MessagingAddress getMessagingAddress(String address);
	
	public List<MessagingAddress> findMessagingAddresses(String address, Protocol protocol, Person person);
	
	public List<MessagingAddress> getPublicAddressesForPerson(Person p);
}
