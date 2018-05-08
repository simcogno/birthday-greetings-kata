package it.xpug.kata.birthday_greetings;
import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.*;



public class EmployeeTest {
	

	
	@Test
	public void checkIfIsBirthday() throws ParseException {
		Employee employee = new Employee("First", "Last", "1999/09/01", "first@last.com");
		assertTrue(employee.isBirthday(new XDate("2008/09/01")));
	}

	@Test
	public void checkIfNotBirthday() throws Exception {
		Employee employee = new Employee("foo", "bar", "1990/01/31", "a@b.c");
		assertFalse(employee.isBirthday(new XDate("2008/01/30")));
	}
	
	@Test
	public void checkIfTwoEmployeesAreTheSameEmployee() throws Exception {
		Employee base = new Employee("First", "Last", "1999/09/01", "first@last.com");
		Employee same = new Employee("First", "Last", "1999/09/01", "first@last.com");
		assertTrue(base.equals(same));	
	}

	@Test
	public void equality() throws Exception {
		Employee base = new Employee("First", "Last", "1999/09/01", "first@last.com");
		Employee same = new Employee("First", "Last", "1999/09/01", "first@last.com");
		Employee different = new Employee("First", "Last", "1999/09/01", "boom@boom.com");

		assertFalse(base.equals(null));
		assertFalse(base.equals(""));
		assertTrue(base.equals(same));
		assertFalse(base.equals(different));
	}
}
