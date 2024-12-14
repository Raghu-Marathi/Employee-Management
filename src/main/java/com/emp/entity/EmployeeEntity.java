package com.emp.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name="Employee_Data")
public class EmployeeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer employeeId;
	
	private String employeeName;
	
	private Double employeeSalary;
	
	private String employeeAddress;
	
	private String employeeDesignation;
	
	private String employeeOrgnization;
	
	
	private String createdBy;
	
	private String updatedBy;
	
	@CreationTimestamp
	@Column(name="Created_Date" , updatable = false)     // updatable = false  means while performing operation on this property ,below update date column will not work
	private LocalDateTime createDate;
	
	@UpdateTimestamp
	@Column(name="Updated_Date" , insertable = false)
	private LocalDateTime updatedDate;
	
	

}
