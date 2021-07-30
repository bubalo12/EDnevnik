package com.iktpreobuka.EDnevnik.utils;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


import com.iktpreobuka.EDnevnik.entities.dto.TeacherRegisterDTO;


@Component
public class TeacherCustomValidator implements Validator {

	@Override
	public boolean supports(Class<?> myClass) {
		return TeacherRegisterDTO.class.equals(myClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		TeacherRegisterDTO teacher = (TeacherRegisterDTO) target;

		if (!teacher.getPassword().equals(teacher.getConfirmPassword())) {
			errors.reject("400", "Passwords must be the same");
		}
		else if (!teacher.getUsername().equals(teacher.getUsername())) {
			errors.reject("400", "Username already in use!");
		}

	}

}
