package com.iktpreobuka.EDnevnik.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table (name = "Students")
public class StudentEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer studentId;
	
	@Column(name = "username", nullable = false, unique = true)
	@NotBlank(message = "Username must be provided.")
	@Size(min = 5, max = 20, message = "Username must be between {min} and {max} characters long.")
	private String username;
	
	@JsonIgnore
	@Column(name = "password", nullable = false)
	@NotBlank(message = "Password must be provided.")
	@Size(min = 5, max = 20, message = "Password must be between {min} and {max} characters long.")
	private String password;
	
	@Column(nullable = false)
	@NotBlank(message = "First name must be provided.")
	@Size(min=2, max=30, message = "First name must be between {min} and {max} characters long.")
	private String firstName;
	
	@Column(nullable = false)
	@NotBlank(message = "Last name must be provided.")
	@Size(min=2, max=30, message = "First name must be between {min} and {max} characters long.")
	private String lastName;
	
	@Column(nullable = false, unique = true)
	@NotNull(message = "JMBG must be provided.")
	@Pattern(regexp = "^\\d{13}$", message = "JMBG must contains 13 characters.")
	private String jmbg;
	
	private EUserRole role;
	
	@JsonManagedReference
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "userEntity")
	private UserEntity userEntity;
	
	@JsonManagedReference
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "gradeRazredEntity")
	private Grade_RazredEntity gradeRazredEntity;

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

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
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

	public StudentEntity () {
		super();
	}

}
