package com.practical.sb.Pracsbcrud.service;

import java.util.List;

import com.practical.sb.Pracsbcrud.model.Employee;

public interface EmployeeService{

	Employee saveEmployee(Employee employee);			//method is saveEmployee and return type of this method is Employee and passed an employee object

	List<Employee> getAllEmployees();
	
	Employee getEmployeeById(long id);
	
	Employee updateEmployee(Employee employee, long id);
	
	void deleteEmployee(long id);
}
