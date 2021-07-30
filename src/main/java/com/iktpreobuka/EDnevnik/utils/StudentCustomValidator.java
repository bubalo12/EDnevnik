package com.iktpreobuka.EDnevnik.utils;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.iktpreobuka.EDnevnik.entities.dto.AdminRegisterDTO;
import com.iktpreobuka.EDnevnik.entities.dto.StudentRegisterDTO;


@Component
public class StudentCustomValidator implements Validator {

	@Override
	public boolean supports(Class<?> myClass) {
		return StudentRegisterDTO.class.equals(myClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		StudentRegisterDTO student = (StudentRegisterDTO) target;

		if (!student.getPassword().equals(student.getConfirmPassword())) {
			errors.reject("400", "Passwords must be the same");
		}
		else if (!student.getUsername().equals(student.getUsername())) {
			errors.reject("400", "Username already in use!");
		}

	}

}
