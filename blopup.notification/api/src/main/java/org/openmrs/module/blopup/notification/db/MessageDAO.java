package org.openmrs.module.blopup.notification.db;

import org.openmrs.Person;
import org.openmrs.api.APIException;
import org.openmrs.module.blopup.notification.MessageService;
import org.openmrs.module.blopup.notification.domain.Message;
import org.openmrs.module.blopup.notification.domain.gateway.Protocol;

import java.util.List;

/**
 * @author Dieterich
 */
public interface MessageDAO {
	
	/**
	 * @see MessageService#getAllMessages()
	 */
	public List<Message> getAllMessages();
	
	/**
	 * @see MessageService#getMessage(Integer)
	 */
	public Message getMessage(Integer messageId);
	
	/**
	 */
	public List<Message> findMessagesWithAddresses(Protocol protocol, String toAddress, String fromAddress, String content,
	        Integer status);
	
	/**
	 */
	public List<Message> findMessagesWithPeople(Protocol protocol, Person sender, Person recipient, String content,
	        Integer status);
	
	/**
	 * @see MessageService#saveMessage(Message)
	 */
	public void saveMessage(Message message) throws APIException;
	
	/**
	 * @see MessageService#deleteMessage(Message)
	 */
	public void deleteMessage(Message message) throws APIException;
	
	public List<Message> getOutboxMessages();
	
	public List<Message> getOutboxMessagesByProtocol(Protocol p);
}
