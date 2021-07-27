package com.iktpreobuka.EDnevnik.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Admins")
public class AdminEntity extends UserEntity{

	public AdminEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AdminEntity(Integer id,
			@NotBlank(message = "Username must be provided.") @Size(min = 5, max = 20, message = "Username must be between {min} and {max} characters long.") String username,
			@NotBlank(message = "Password must be provided.") @Size(min = 5, max = 100, message = "Password must be between {min} and {max} characters long.") String password,
			EUserRole role) {
		super(id, username, password, role);
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}
