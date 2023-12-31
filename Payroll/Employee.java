package Payroll;

import java.math.BigInteger;

public class Employee  {
	
	private String username = "";
	private String employeeName = "";
	private double hoursWorked = 0.0;
	private double hourlyRate = 0.0;
	private boolean clockedIn = false;
	private BigInteger[] password = null;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public double getHoursWorked() {
		return hoursWorked;
	}
	public void setHoursWorked(double hoursWorked) {
		this.hoursWorked = hoursWorked;
	}
	public double getHourlyRate() {
		return hourlyRate;
	}
	public void setHourlyRate(double hourlyRate) {
		this.hourlyRate = hourlyRate;
	}
	public boolean isClockedIn() {
		return clockedIn;
	}
	public void setClockedIn(boolean clockedIn) {
		this.clockedIn = clockedIn;
	}
	public String getPassword() {
		return new RSAEncryption().decryptPassword(this.password);
	}
	public void setPassword(String password) {
		this.password = new RSAEncryption().encryptPassword(password);
	}
	
	public double calculatePay() {
		return this.getHoursWorked() * this.getHourlyRate();
	}
	
	
}