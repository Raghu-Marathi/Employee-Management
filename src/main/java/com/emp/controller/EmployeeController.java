package com.emp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.emp.binding.EmployeeRequest;
import com.emp.binding.EmployeeResponse;
import com.emp.service.EmployeeService;

@RestController
@Validated
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@PostMapping("/employee")
	public ResponseEntity<String> saveNewEmployeeData(@Valid @RequestBody EmployeeRequest employeeRequest) {
		
		boolean result = employeeService.saveEmployeeData(employeeRequest);
		if(result == true) {
			return new ResponseEntity<String>("Data Inserted Successfully",HttpStatus.CREATED);
		}
		return new ResponseEntity<String>("Data Not Inserted",HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/employee/id/{id}")                                          // if  given like this("/employee/{id}")  , some other end point maybe like ("/employee/{age}")  then ambiguity (confusion)            
	public ResponseEntity<EmployeeResponse> getDataBasedOnId(@PathVariable Integer id) {
		
		EmployeeResponse employeeResponse = employeeService.getEmployeeDataBasedOnId(id);
		if(employeeResponse != null) {
			return new ResponseEntity<EmployeeResponse>(employeeResponse,HttpStatus.OK);
		}else {
			return new ResponseEntity<EmployeeResponse>(employeeResponse,HttpStatus.BAD_REQUEST);   // or HttpStatus.INTERNAL_SERVER_ERROR
		}
	}
	
	@GetMapping("/employee/name/{name}")                                    // must be match path variable and passed parameter names
	public ResponseEntity<List<EmployeeResponse>> getDataBasedOnEmpName(@PathVariable String name){
		List<EmployeeResponse> listOfDataByName = employeeService.getEmployeeDataBasedOnName(name);
		if(listOfDataByName != null) {
			return new ResponseEntity<List<EmployeeResponse>>(listOfDataByName, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<EmployeeResponse>>(listOfDataByName, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/employee/address/{address}")
	public ResponseEntity<List<EmployeeResponse>> getDataBasedOnEmpAddress(@PathVariable String address){
		List<EmployeeResponse> listOfDataByAddress = employeeService.getEmployeeDataBasedOnAddress(address);
		if(listOfDataByAddress != null) {
			return new ResponseEntity<List<EmployeeResponse>>(listOfDataByAddress, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<List<EmployeeResponse>>(listOfDataByAddress, HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/employee/update/{id}")
	public ResponseEntity<EmployeeResponse> updateEmployeeDetails(@RequestBody EmployeeRequest employeeRequest,@PathVariable Integer id){
		
		EmployeeResponse response = employeeService.updateEmployeeData(employeeRequest, id);
		if(response != null) {
			return new ResponseEntity<EmployeeResponse>(response,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<EmployeeResponse>(response, HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/employee/delete/{id}")
	public ResponseEntity<String> deleteEmployeeDetailesById(@PathVariable Integer id){
		boolean result = employeeService.deleteEmployeeData(id);
		if(result == true) {
			return new ResponseEntity<String>("Data deleted successfully",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Data not deleted ",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	

}
