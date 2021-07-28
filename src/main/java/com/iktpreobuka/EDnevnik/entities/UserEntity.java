package com.iktpreobuka.EDnevnik.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Users")
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "username", nullable = false, unique = true)
	@NotBlank(message = "Username must be provided.")
	@Size(min = 5, max = 20, message = "Username must be between {min} and {max} characters long.")
	private String username;
	
	@JsonIgnore
	@Column(name = "password", nullable = false)
	@NotBlank(message = "Password must be provided.")
	@Size(min = 5, max = 20, message = "Password must be between {min} and {max} characters long.")
	private String password;
	
	private EUserRole role;
	
	@JsonBackReference
	@OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	protected List<StudentEntity> students = new ArrayList<>();
	
	@JsonBackReference
	@OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	protected List<TeacherEntity> teachers = new ArrayList<>();
	
	@JsonBackReference
	@OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	protected List<ParentEntity> parents = new ArrayList<>();
	
	@JsonBackReference
	@OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	protected List<AdminEntity> admins = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public UserEntity(Integer id,
			@NotBlank(message = "Username must be provided.") @Size(min = 5, max = 20, message = "Username must be between {min} and {max} characters long.") String username,
			@NotBlank(message = "Password must be provided.") @Size(min = 5, max = 100, message = "Password must be between {min} and {max} characters long.") String password,
			EUserRole role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
	}
	
	public UserEntity() {
		super();
	}

}
