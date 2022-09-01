package com.practical.sb.Pracsbcrud.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practical.sb.Pracsbcrud.exception.ResourceNotFoundException;
import com.practical.sb.Pracsbcrud.model.Employee;
import com.practical.sb.Pracsbcrud.repository.EmployeeRepository;
import com.practical.sb.Pracsbcrud.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);				//use employeeRepository, save method and save the employee instance to the database
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(long id) {
//		Optional<Employee> employee = employeeRepository.findById(id);
//		
//		if(employee.isPresent()) {
//			return employee.get();
//		}
//		else {
//			throw new ResourceNotFoundException("Employee", "Id", id);
//		}
		
		return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));	//lambda expression
	}

	@Override
	public Employee updateEmployee(Employee employee, long id) {
		
		//we need to check whether the employee with given id is existing in database or not
		Employee existingEmployee = employeeRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Employee", "Id", id));
		
		existingEmployee.setFirstName(employee.getFirstName());			//update new employees to existing employee
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());
		
		// save existing employees to database
		employeeRepository.save(existingEmployee);
		return existingEmployee;
	}

	@Override
	public void deleteEmployee(long id) {
		
		//check whether employee exist in database or not
		employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));
		
		employeeRepository.deleteById(id);		//employee id which is passed in argument is deleted. but before deleting, we must check whether the employee is existing in DB or not. if it is existing it will delete but if not, it need to throw an exception. so before delete we must create findById method to check the employee.
	}

}
