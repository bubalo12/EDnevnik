package com.iktpreobuka.EDnevnik.entities.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.iktpreobuka.EDnevnik.entities.EUserRole;

public class UserRegisterDTO {
	
	@NotBlank(message = "Username must be provided.")
	@Size(min = 5, max = 20, message = "Username must be between {min} and {max} characters long.")
	private String username;
	
	@NotBlank(message = "Password must be provided.")
	@Size(min = 5, max = 20, message = "Password must be between {min} and {max} characters long.")
    private String password;
	
	private EUserRole role;

	public UserRegisterDTO() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public EUserRole getRole() {
		return role;
	}

	public void setRole(EUserRole role) {
		this.role = role;
	}
	
	

}
