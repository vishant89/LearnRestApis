package com.rest.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.rest.exception.ResourceNotFoundException;
import com.rest.model.Employee;
import com.rest.repository.EmployeeRepository;
import com.rest.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	
	private EmployeeRepository employeeRepository;
	
	
	
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}




	@Override
	public Employee saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return employeeRepository.save(employee);
	}




	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}


	@Override
	public Employee getEmployeeById(long id) {
//		Optional<Employee> employee = employeeRepository.findById(id);
//		if(employee.isPresent()) {
//			return employee.get();
//		}else {
//			throw new ResourceNotFoundException("Employee", "Id", id);
//		}
		return employeeRepository.findById(id).orElseThrow(() -> 
						new ResourceNotFoundException("Employee", "Id", id));
		
	}




	@Override
	public Employee updateEmployee(Employee employee, long id) {
		// TODO Auto-generated method stub
		Employee existingEmployee = employeeRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Employee", "Id", id)); 
		
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());
		// save existing employee to DB
		employeeRepository.save(existingEmployee);
		return existingEmployee;
	}




	@Override
	public void deleteEmployee(long id) {
		
		
		// TODO Auto-generated method stub
		
		employeeRepository.findById(id).orElseThrow(() -> 
		new ResourceNotFoundException("Employee", "Id", id));
employeeRepository.deleteById(id);
		
	}


	

}
