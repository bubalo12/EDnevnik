package com.iktpreobuka.EDnevnik.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Parents")
public class ParentEntity extends UserEntity{
	
	@Column(nullable = false)
	@NotBlank(message = "First name must be provided.")
	@Size(min=2, max=30, message = "First name must be between {min} and {max} characters long.")
	private String firstName;
	
	@Column(nullable = false)
	@NotBlank(message = "Last name must be provided.")
	@Size(min=2, max=30, message = "First name must be between {min} and {max} characters long.")
	private String lastName;
	
	@Column(nullable = false, unique = true)
	@NotNull(message = "Email must be provided.")
	@Email(message = "Email is not valid.")
	private String email;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ParentEntity(Integer id,
			@NotBlank(message = "Username must be provided.") @Size(min = 5, max = 20, message = "Username must be between {min} and {max} characters long.") String username,
			@NotBlank(message = "Password must be provided.") @Size(min = 5, max = 100, message = "Password must be between {min} and {max} characters long.") String password,
			EUserRole role,
			@NotBlank(message = "First name must be provided.") @Size(min = 2, max = 30, message = "First name must be between {min} and {max} characters long.") String firstName,
			@NotBlank(message = "Last name must be provided.") @Size(min = 2, max = 30, message = "First name must be between {min} and {max} characters long.") String lastName,
			@NotNull(message = "Email must be provided.") @Email(message = "Email is not valid.") String email) {
		super(id, username, password, role);
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	
	public ParentEntity() {
		super();
	}
	
	

}
