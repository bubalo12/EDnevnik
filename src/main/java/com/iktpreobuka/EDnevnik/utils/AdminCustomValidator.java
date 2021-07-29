package com.iktpreobuka.EDnevnik.utils;

import javax.validation.Valid;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.iktpreobuka.EDnevnik.entities.dto.AdminRegisterDTO;

@Component
public class AdminCustomValidator implements Validator {

	@Override
	public boolean supports(Class<?> myClass) {
		return AdminRegisterDTO.class.equals(myClass);
	}

	@Override
	public void validate(Object target, Errors errors) {
		AdminRegisterDTO admin = (AdminRegisterDTO) target;

		if (!admin.getPassword().equals(admin.getConfirmPassword())) {
			errors.reject("400", "Passwords must be the same");
		}
		else if (!admin.getUsername().equals(admin.getUsername())) {
			errors.reject("400", "Username already in use!");
		}

	}

	

}
