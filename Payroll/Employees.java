package Payroll;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Employees {
	// map for employees with username as the key and user object as the value
	static Map<String, Employee> employeeMap = new HashMap<>();

	// function to add employees
	public void addEmployee(String username, Employee employee){
		employeeMap.put(username, employee);		
	}
	
	public Employee getEmployee(String username) {
		return employeeMap.get(username);
	}
	
	
	// I think for these next actions it would be best to do these
	// on an employee object we return from the map
	
	// function to add hours for employee	
	// function to retrieve employee passwords	
	// function to retrieve employee hours worked
	// function to retrieve employees pay rate
	// function to calculate payout due

	// print payroll
	// loop through all and show employee name, pay rate, hours worked, and wages
	// earned
	public void printEmployeePayrolls() {
		for (Entry<String, Employee> set :
			employeeMap.entrySet()) {			
			Employee employee = set.getValue();
			System.out.println("Employee Name: " + employee.getUsername() + 
					" Pay this week is $"+ employee.calculatePay());
		}
	}

	// Can do this in Employee object by setting clockedIn to true
	// function for employees to clock in
	
	// Can construct Employee object with these values
	// take in username and password and hours worked
	
	//TODO ITEMS
	// retrieve encrypted password from map
	// decrypt the password
	// verify that the decrypted password matches the password entered
	// if yes call function to add employee hours
	// display current payout due

}
