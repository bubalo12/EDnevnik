package com.iktpreobuka.EDnevnik.entities.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Grade_OcenaRegisterDTO {
	
	@Column(name = "grade", nullable = false)
	@NotNull(message = "Grade must be provided.")
	@Min(value = 1, message = "Grade must be greater than {value}.")
	@Max(value = 5, message = "Grade must be less than {value}.")
	private Integer grade;
	
	@Column(name = "date", nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	protected LocalDate date;

	public Grade_OcenaRegisterDTO() {
		super();
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

}
