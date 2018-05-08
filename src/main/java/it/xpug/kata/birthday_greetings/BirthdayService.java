package it.xpug.kata.birthday_greetings;



public class BirthdayService {
	
	private EmployeeRepository empRepo;
	private Greeter msgSender;
	
	public BirthdayService(EmployeeRepository empRepo, Greeter msgSender) {
		this.empRepo = empRepo;
		this.msgSender = msgSender;
	}

	public void sendGreetings(XDate today) {
		
		try {
			msgSender.send(empRepo.employeesWhoHaveBirthday(today));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

}
