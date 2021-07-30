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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table (name = "Razred")
public class Grade_RazredEntity {
	
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@JsonBackReference
	@OneToMany(mappedBy = "gradeRazredEntity", fetch = FetchType.LAZY, cascade = { CascadeType.REFRESH })
	protected List<StudentEntity> students = new ArrayList<>();

	@Column(name = "Class", nullable = false)
	@NotNull(message = "Class number must be provided.")
	@Min(value = 1, message = "Class number must be greater than {value}.")
	@Max(value = 8, message = "Class number must be less than {value}.")
	private Integer classNo;

	@Column(name = "Department", nullable = false)
	@NotNull(message = "Department number must be provided.")
	@Min(value = 1, message = "Department number must be greater than {value}.")
	@Max(value = 3, message = "Department number must be less than {value}.")
	private Integer departmentNo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Grade_RazredEntity () {
		super();
	}
	
}
