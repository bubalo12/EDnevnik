package com.iktpreobuka.EDnevnik.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table (name = "Razred")
public class Grade_RazredEntity {
	
	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "Class", nullable = false)
	@NotNull(message = "Class number must be provided.")
	@Min(value = 1, message = "Class number must be greater than {value}.")
	@Max(value = 8, message = "Class number must be less than {value}.")
	private Integer classNo;

	@Column(name = "Department", nullable = false)
	@NotNull(message = "Department number must be provided.")
	@Min(value = 1, message = "Department number must be greater than {value}.")
	@Max(value = 3, message = "Department number must be less than {value}.")
	private Integer deparmentNo;

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

	public Integer getDeparmentNo() {
		return deparmentNo;
	}

	public void setDeparmentNo(Integer deparmentNo) {
		this.deparmentNo = deparmentNo;
	}

	public Grade_RazredEntity(Integer id,
			@NotNull(message = "Class number must be provided.") @Min(value = 1, message = "Class number must be greater than {value}.") @Max(value = 8, message = "Class number must be less than {value}.") Integer classNo,
			@NotNull(message = "Department number must be provided.") @Min(value = 1, message = "Department number must be greater than {value}.") @Max(value = 3, message = "Department number must be less than {value}.") Integer deparmentNo) {
		super();
		this.id = id;
		this.classNo = classNo;
		this.deparmentNo = deparmentNo;
	}

	public Grade_RazredEntity () {
		super();
	}
	
}
