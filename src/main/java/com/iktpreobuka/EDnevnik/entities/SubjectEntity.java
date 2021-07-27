package com.iktpreobuka.EDnevnik.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table (name = "Subjects")
public class SubjectEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "name", nullable = false)
	@NotBlank(message = "Name must be provided.")
	@Size(min = 5, max = 25, message = "Username must be between {min} and {max} characters long.")
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SubjectEntity(Integer id,
			@NotBlank(message = "Name must be provided.") @Size(min = 5, max = 25, message = "Username must be between {min} and {max} characters long.") String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public SubjectEntity () {
		super();
	}

}
