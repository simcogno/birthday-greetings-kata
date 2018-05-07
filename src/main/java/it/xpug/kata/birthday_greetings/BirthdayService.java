package it.xpug.kata.birthday_greetings;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class BirthdayService {
	
	private MessageSender msgSender;
	private List<Employee> employeeWhoseBirthdayIsToday;
	
	public BirthdayService(MessageSender msgSender) {
		this.msgSender = msgSender;
		employeeWhoseBirthdayIsToday = new ArrayList<Employee>();
	}

	public void sendGreetings(String fileName, XDate xDate) throws IOException, ParseException, AddressException, MessagingException {
		BufferedReader in = new BufferedReader(new FileReader(fileName));
		String str = "";
		str = in.readLine(); // skip header
		while ((str = in.readLine()) != null) {
			String[] employeeData = str.split(", ");
			Employee employee = new Employee(employeeData[1], employeeData[0], employeeData[2], employeeData[3]);
			if (employee.isBirthday(xDate)) {
				employeeWhoseBirthdayIsToday.add(employee);
			}
		}
		send();
	}
	

	
	private void send() throws AddressException, MessagingException {
		msgSender.sendMessage(employeeWhoseBirthdayIsToday);
	}

}
