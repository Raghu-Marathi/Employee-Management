package com.emp.binding;


import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class EmployeeRequest {
	
	@NotNull(message = "employeeName should not be null")
	@Size(min = 3,max = 20, message = "employeeName must be between 3  and 20 characters" )
	//@Min(3)
    private String employeeName;
	
	@NotNull(message = "employeeSalary should not be null")
	@DecimalMin(value = "1000.00" ,inclusive = true, message = "salary must be >=1000 ")
	@DecimalMax(value = "99999999.00" ,inclusive = true, message = "salary must be less than 99999999 ")
	private Double employeeSalary;
	
	@NotNull(message = "employeeAddress should not be null")
	@Size(min=3, max=20 , message = "employeeAddress must be between 3 to 20 characters" )
	private String employeeAddress;
	
	@NotNull(message = "employeeDesignation should not be null")
	@Size(min=2, max=20 , message = "employeeDesignation must be between 2 to 20 characters" )
	private String employeeDesignation;
	
	//@NotBlank( message = "employeeOrganization should not be blank")   //@NotBlank =@NotNull + @Size(min=1)
	@Size(min=2, max=20 , message = "employeeOrganization must be between 2 to 20 characters" )
	private String employeeOrgnization;

}
