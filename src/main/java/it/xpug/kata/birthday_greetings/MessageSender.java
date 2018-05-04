package it.xpug.kata.birthday_greetings;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public interface MessageSender {
	
	public void sendMessage(List<Employee> employeeThatHasBirthdayToday) throws AddressException, MessagingException;

}
