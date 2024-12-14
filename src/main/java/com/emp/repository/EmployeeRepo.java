package com.emp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emp.entity.EmployeeEntity;

public interface EmployeeRepo extends JpaRepository<EmployeeEntity, Integer>{
	
	public List<EmployeeEntity> findByEmployeeName(String employeeName);
	
	public List<EmployeeEntity> findByEmployeeAddress(String employeeAddress);

}
