package it.xpug.kata.birthday_greetings;

import static org.junit.Assert.*;

import org.junit.*;

import com.dumbster.smtp.*;


public class AcceptanceTest {

	private static final int NONSTANDARD_PORT = 9999;
	private BirthdayService birthdayService;
	private SimpleSmtpServer mailServer;

	@Before
	public void setUp() throws Exception {
		mailServer = SimpleSmtpServer.start(NONSTANDARD_PORT);
		birthdayService = new BirthdayService(new TxtEmployeeRepository(), new SMTPGreeter());
	}

	@After
	public void tearDown() throws Exception {
		mailServer.stop();
		Thread.sleep(200);
	}
	
	@Test
	public void willNotSendEmailsWhenNobodysBirthday() throws Exception {
		
		birthdayService.sendGreetings(new XDate("2008/01/01"));
		assertEquals(0, mailServer.getReceivedEmailSize());
	}
	
	@Test
	public void ifSomeoneHasBirthdayAnEmailIsSend() throws Exception {
		birthdayService.sendGreetings(new XDate("2008/10/08"));
		assertEquals(1, mailServer.getReceivedEmailSize());
	}
	
	@Test
	public void emailBodyIsCorrect() throws Exception {
		birthdayService.sendGreetings(new XDate("2008/10/08"));
		SmtpMessage message = (SmtpMessage) mailServer.getReceivedEmail().next();
		assertEquals("Happy Birthday, dear John", message.getBody());
	}
	
	@Test
	public void subjectOfGreetingsEmailIsCorrect() throws Exception {
		birthdayService.sendGreetings(new XDate("2008/10/08"));
		SmtpMessage message = (SmtpMessage) mailServer.getReceivedEmail().next();
		assertEquals("Happy Birthday!", message.getHeaderValue("Subject"));
	}
	
	@Test
	public void allEmployeesWhoHaveBirthdayReceivesGreetings() throws Exception {
		birthdayService.sendGreetings(new XDate("2008/10/08"));
		SmtpMessage message = (SmtpMessage) mailServer.getReceivedEmail().next();
		String[] recipients = message.getHeaderValues("To");
		assertEquals(1, recipients.length);
	}
	
	@Test
	public void greetingsAreSendToRightEmployees() throws Exception {
		birthdayService.sendGreetings(new XDate("2008/10/08"));
		SmtpMessage message = (SmtpMessage) mailServer.getReceivedEmail().next();
		String[] recipients = message.getHeaderValues("To");
		assertEquals("john.doe@foobar.com", recipients[0].toString());
	}

}
