package it.xpug.kata.birthday_greetings;

import java.util.List;

public interface EmployeeRepository {
	
	public List<Employee> selectAll();
	public List<Employee> employeesWhoHaveBirthday(XDate today);
}
