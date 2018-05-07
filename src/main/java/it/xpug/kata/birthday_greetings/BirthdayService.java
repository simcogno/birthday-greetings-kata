package it.xpug.kata.birthday_greetings;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public class BirthdayService {
	
	private EmployeeParser empParser;
	private MessageSender msgSender;
	private List<Employee> employeeWhoseBirthdayIsToday;
	
	public BirthdayService(EmployeeParser empParser, MessageSender msgSender) {
		this.empParser = empParser;
		this.msgSender = msgSender;
		employeeWhoseBirthdayIsToday = new ArrayList<Employee>();
	}

	public void sendGreetings(XDate xDate) throws FileNotFoundException, IOException, ParseException, AddressException, MessagingException  {
		
		for(Employee employee : empParser.collectEmployees()) {
			if (employee.isBirthday(xDate)) {
				employeeWhoseBirthdayIsToday.add(employee);
			}
		}
		
		msgSender.sendMessage(employeeWhoseBirthdayIsToday);
		
	}
	

}
