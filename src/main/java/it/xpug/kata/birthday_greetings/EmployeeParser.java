package it.xpug.kata.birthday_greetings;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface EmployeeParser {
	
	public List<Employee> collectEmployees() throws FileNotFoundException, IOException, ParseException;

}
