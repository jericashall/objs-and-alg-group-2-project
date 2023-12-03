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
}
