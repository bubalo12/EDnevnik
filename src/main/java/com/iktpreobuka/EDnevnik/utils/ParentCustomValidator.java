package com.iktpreobuka.EDnevnik.utils;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.iktpreobuka.EDnevnik.entities.dto.ParentRegisterDTO;


@Component
public class ParentCustomValidator implements Validator {

	@Override
	public boolean supports(Class<?> myClass) {
		return ParentRegisterDTO.class.equals(myClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ParentRegisterDTO parent = (ParentRegisterDTO) target;

		if (!parent.getPassword().equals(parent.getConfirmPassword())) {
			errors.reject("400", "Passwords must be the same");
		}
		else if (!parent.getUsername().equals(parent.getUsername())) {
			errors.reject("400", "Username already in use!");
		}

	}
}
