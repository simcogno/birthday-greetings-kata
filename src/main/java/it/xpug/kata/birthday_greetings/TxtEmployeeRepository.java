package it.xpug.kata.birthday_greetings;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class TxtEmployeeRepository implements EmployeeRepository {
	
	private List<Employee> allEmployees = new ArrayList<Employee>();
	private String fileName = "employee_data.txt";

	public List<Employee> selectAll() {
		
		try {
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			String str = "";
			str = in.readLine(); // skip header
			while ((str = in.readLine()) != null) {
				String[] employeeData = str.split(", ");
				Employee employee = new Employee(employeeData[1], employeeData[0], employeeData[2], employeeData[3]);
				allEmployees.add(employee);
			} 
		} catch (Exception e) {
				e.printStackTrace();
		} 
				
		return allEmployees;
	}

	public List<Employee> employeesWhoHaveBirthday(XDate today) {
		
		List<Employee> eWhoHaveBirthday = new ArrayList<Employee>();
		
		try {
			selectAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(Employee e : allEmployees) {
			if(e.isBirthday(today))
				eWhoHaveBirthday.add(e);
		}
			
		return eWhoHaveBirthday;
	}
	

}
