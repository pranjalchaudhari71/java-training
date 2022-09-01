package com.practical.sb.Pracsbcrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.practical.sb.Pracsbcrud.model.Employee;
import com.practical.sb.Pracsbcrud.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired	
    private EmployeeService employeeService;
    

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}
	
	
	//build create employee REST API	  
	@PostMapping() 																			//post request contains Employee JSON object that needs to bind to the employee java object 
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){ 					//method pass an employee as an object
		return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED); 		//REST API will create the resource 
	}
	 
	
//	@PostMapping("/save")
//	public String saveEmployee(@RequestBody Employee employee) {
//		return employeeService.saveEmployee(employee);
//		
//	}
	
	//build get all employee REST API
	@GetMapping()
	public List<Employee> getAllEmployees() {
		 return employeeService.getAllEmployees();
	}
	
	//build get employee by Id REST API
	@GetMapping("{id}")							//http://localhost:8080/api/employees/1
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeId){
		return new ResponseEntity<Employee>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);	//http status is 200 i.e, OK in postman;		//response entity must handle two parameters i.e, body and status
		// return type must be ResponseEntity, because it is used to prefer response of REST API, and also we can add status, body, header to this class.
	}

	//build update employee REST API
	@PutMapping("{id}")									//http://localhost:8080/api/employees/1
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id,	@RequestBody Employee employee){		//updated employee is stored in @req Employee(Json is stored) and @RequestBody is used to convert JSON to java object and that java object is stored on DB.
		return new ResponseEntity<Employee>(employeeService.updateEmployee(employee, id), HttpStatus.OK);
	}
	
	//build delete employee REST API
	@DeleteMapping("{id}")								//http://localhost:8080/api/employees/1
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id){
		// delete employee from database
		employeeService.deleteEmployee(id);			//delete employee method is called from employee service.
		return new ResponseEntity<String>("Employee deleted successfully!.", HttpStatus.OK);
	}
}



