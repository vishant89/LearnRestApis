package com.rest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.model.Employee;
import com.rest.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
   private EmployeeService employeeservice;

public EmployeeController(EmployeeService employeeservice) {
	super();
	this.employeeservice = employeeservice;
}

@PostMapping()
public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
	return new ResponseEntity<Employee>(employeeservice.saveEmployee(employee), HttpStatus.CREATED);
}

@GetMapping
public List<Employee> getAllEmployees(){
	return employeeservice.getAllEmployees();
}

@GetMapping("{id}")
public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeId){
	return new ResponseEntity<Employee>(employeeservice.getEmployeeById(employeeId), HttpStatus.OK);
}



@PutMapping("{id}")
public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id
											  ,@RequestBody Employee employee){
	return new ResponseEntity<Employee>(employeeservice.updateEmployee(employee, id), HttpStatus.OK);
}

@DeleteMapping("{id}")
public ResponseEntity<String> deleteEmployee(@PathVariable("id") long id){
	
	// delete employee from DB
	employeeservice.deleteEmployee(id);
	
	return new ResponseEntity<String>("Employee deleted successfully!.", HttpStatus.OK);
}






}
