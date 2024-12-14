package com.emp.service;

import java.util.List;

import com.emp.binding.EmployeeRequest;
import com.emp.binding.EmployeeResponse;

public interface EmployeeService {
	                                                      // if we don't know the exact implementation then use interfaces 
	
	public boolean saveEmployeeData(EmployeeRequest empployeeRequest);
	
	public EmployeeResponse getEmployeeDataBasedOnId(Integer employeeId);
	
	public List<EmployeeResponse> getEmployeeDataBasedOnName(String employeeName);

	public List<EmployeeResponse> getEmployeeDataBasedOnAddress(String employeeAddress);
	
	public EmployeeResponse updateEmployeeData(EmployeeRequest employeeRequest,Integer employeeId); // update operation level first it verifies particular record available or not if available update operation will perform
	                                                                                            // if record not available update operation will not perform
	                                                                                           //first select query then update query.
	public boolean deleteEmployeeData(Integer employeeId);
}
