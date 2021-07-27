package com.iktpreobuka.EDnevnik.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table (name = "Ocene")
public class Grade_OcenaEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "grade", nullable = false)
	@NotNull(message = "Grade must be provided.")
	@Min(value = 1, message = "Grade must be greater than {value}.")
	@Max(value = 5, message = "Grade must be less than {value}.")
	private Integer grade;
	
	@Column(name = "date", nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	protected LocalDate date;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Grade_OcenaEntity(Integer id,
			@NotNull(message = "Grade must be provided.") @Min(value = 1, message = "Grade must be greater than {value}.") @Max(value = 5, message = "Grade must be less than {value}.") Integer grade,
			LocalDate date) {
		super();
		this.id = id;
		this.grade = grade;
		this.date = date;
	}

	public Grade_OcenaEntity() {
		super();
	}
	
	

}
