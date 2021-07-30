package com.iktpreobuka.EDnevnik.entities.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SubjectRegisterDTO {

	
	@NotBlank(message = "Name must be provided.")
	@Size(min = 5, max = 25, message = "Subject name must be between {min} and {max} characters long.")
	private String name;

	public SubjectRegisterDTO() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
