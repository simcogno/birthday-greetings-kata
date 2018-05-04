package it.xpug.kata.birthday_greetings;

import java.util.List;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SMTPSender implements MessageSender {
	
	private static final int smtpPort = 9999;
	private static final String smtpHost = "localhost";
	private String body = "Happy Birthday, dear ";
	private String subject = "Happy Birthday!";
	private String sender = "sender@here.com";
	
	private Session session;
	
	public SMTPSender() {
		// Create a mail session
		java.util.Properties props = new java.util.Properties();
		props.put("mail.smtp.host", smtpHost);
		props.put("mail.smtp.port", "" + smtpPort);
		session = Session.getInstance(props, null);
	}

	
	public void sendMessage(List<Employee> employeeThatHasBirthdayToday) throws AddressException, MessagingException {
		for(Employee e : employeeThatHasBirthdayToday) {
			
			// Construct the message
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(sender));
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(e.getEmail()));
			msg.setSubject(subject);
			msg.setText(body);

			// Send the message
			Transport.send(msg);
		}
	}

}
