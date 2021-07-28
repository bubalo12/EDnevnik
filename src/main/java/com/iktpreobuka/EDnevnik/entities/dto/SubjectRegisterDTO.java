package com.iktpreobuka.EDnevnik.entities.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SubjectRegisterDTO {

	
	@NotBlank(message = "Name must be provided.")
	@Size(min = 5, max = 25, message = "Username must be between {min} and {max} characters long.")
	private String name;

	public SubjectRegisterDTO(
			@NotBlank(message = "Name must be provided.") @Size(min = 5, max = 25, message = "Username must be between {min} and {max} characters long.") String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
