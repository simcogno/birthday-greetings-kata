package it.xpug.kata.birthday_greetings;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class TxtEmployeeParser implements EmployeeParser {
	
	private List<Employee> allEmployees = new ArrayList<Employee>();
	private String fileName = "employee_data.txt";

	public List<Employee> collectEmployees() throws FileNotFoundException, IOException, ParseException {
		
		BufferedReader in = new BufferedReader(new FileReader(fileName));
		String str = "";
		str = in.readLine(); // skip header
		while ((str = in.readLine()) != null) {
			String[] employeeData = str.split(", ");
			Employee employee = new Employee(employeeData[1], employeeData[0], employeeData[2], employeeData[3]);
			allEmployees.add(employee);
		}
		
		return allEmployees;
	}
	

}
