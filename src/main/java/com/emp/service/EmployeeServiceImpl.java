package com.emp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emp.binding.EmployeeRequest;
import com.emp.binding.EmployeeResponse;
import com.emp.entity.EmployeeEntity;
import com.emp.repository.EmployeeRepo;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired                                                   // means ,injecting one class object into another class
	public EmployeeRepo employeeRepo;                       // this interface internally implemented one class(proxy) that class obj injected here. 
	
	@Override
	public boolean saveEmployeeData(EmployeeRequest empployeeRequest) {
		
		if(empployeeRequest !=null) {
			EmployeeEntity entity=new EmployeeEntity();
			
			BeanUtils.copyProperties(empployeeRequest, entity);   // means copying properties from empployeeRequest and pasting in entity.
			                                                       // copyProperties is a static method
			EmployeeEntity entityResponseData = employeeRepo.save(entity);
			if(entityResponseData !=null) {
				return true;
			}
		}
		return false;
	}

	@Override
	public EmployeeResponse getEmployeeDataBasedOnId(Integer employeeId) {
		
		EmployeeResponse employeeResponse = new EmployeeResponse();    
		
		Optional<EmployeeEntity> optionalEntity = employeeRepo.findById(employeeId);
		if(optionalEntity.isPresent()) {
			EmployeeEntity employeeEntity = optionalEntity.get();
			BeanUtils.copyProperties(employeeEntity, employeeResponse);    // create Response obj bcoz  directly we can not pass entity object as response, and copy entity to response  
		}
		// if data not available empty response will go back
		return employeeResponse;  
	}

	@Override
	public List<EmployeeResponse> getEmployeeDataBasedOnName(String employeeName) {
		List<EmployeeEntity> listOfEmpEntities = employeeRepo.findByEmployeeName(employeeName);
		
		List<EmployeeResponse> listOfEmpResponses=new ArrayList<>();
		for(EmployeeEntity emp:listOfEmpEntities ) {
			EmployeeResponse response=new EmployeeResponse();
			BeanUtils.copyProperties(emp,response );
			listOfEmpResponses.add(response);
			
		}
		return listOfEmpResponses;
	}

	@Override
	public List<EmployeeResponse> getEmployeeDataBasedOnAddress(String employeeAddress) {
		
		List<EmployeeEntity> listOfEmpEntities = employeeRepo.findByEmployeeAddress(employeeAddress);
		
		List<EmployeeResponse> listOfEmpResponses=new ArrayList<>();
		
		for(EmployeeEntity emp:listOfEmpEntities) {
			EmployeeResponse response=new EmployeeResponse();
			BeanUtils.copyProperties(emp, response);
			listOfEmpResponses.add(response);
		}
		return listOfEmpResponses;
	}

	@Override
	public EmployeeResponse updateEmployeeData(EmployeeRequest employeeRequest, Integer employeeId) {

		Optional<EmployeeEntity> optional = employeeRepo.findById(employeeId);
		
		EmployeeResponse updatedResponse=new EmployeeResponse();
		
		if(optional.isPresent()) {
			EmployeeEntity employeeEntity = optional.get();
			if(employeeRequest.getEmployeeName() != null) {
				employeeEntity.setEmployeeName(employeeRequest.getEmployeeName());
			}if(employeeRequest.getEmployeeAddress() != null) {
				employeeEntity.setEmployeeAddress(employeeRequest.getEmployeeAddress());
			}if(employeeRequest.getEmployeeDesignation() != null) {
				employeeEntity.setEmployeeDesignation(employeeRequest.getEmployeeDesignation());
			}if(employeeRequest.getEmployeeOrgnization() != null) {
				employeeEntity.setEmployeeOrgnization(employeeRequest.getEmployeeOrgnization());
			}if(employeeRequest.getEmployeeSalary() != null) {
				employeeEntity.setEmployeeSalary(employeeRequest.getEmployeeSalary());
			}
			   // which properties data we want to update those only set , and here don't use BeanUtils copy ,if we use then here set's data only prints remaining will be nulls. 
			EmployeeEntity updatedEntity = employeeRepo.save(employeeEntity);             // save method works in two ways if data not available then it works like insert operation, if data available then it works as update operation
			
			BeanUtils.copyProperties(updatedEntity, updatedResponse);                        // after updation we can use
			
		}
		return updatedResponse;
	}

	@Override
	public boolean deleteEmployeeData(Integer employeeId) {
		Optional<EmployeeEntity> optional = employeeRepo.findById(employeeId);
		
		if(optional.isPresent()) {
		employeeRepo.deleteById(employeeId);
		return true;
		}
		return false;
	}

}
