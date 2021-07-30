package com.iktpreobuka.EDnevnik.entities.dto;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Grade_RazredRegisterDTO {
	
	
	@NotNull(message = "Class number must be provided.")
	@Min(value = 1, message = "Class number must be greater than {value}.")
	@Max(value = 8, message = "Class number must be less than {value}.")
	private Integer classNo;

	@NotNull(message = "Department number must be provided.")
	@Min(value = 1, message = "Department number must be greater than {value}.")
	@Max(value = 3, message = "Department number must be less than {value}.")
	private Integer departmentNo;

	public Grade_RazredRegisterDTO() {
		super();
	}

	public Integer getClassNo() {
		return classNo;
	}

	public void setClassNo(Integer classNo) {
		this.classNo = classNo;
	}

	public Integer getDepartmentNo() {
		return departmentNo;
	}

	public void setDepartmentNo(Integer departmentNo) {
		this.departmentNo = departmentNo;
	}

	

}
