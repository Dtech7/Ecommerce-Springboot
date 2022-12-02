package com.example.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.models.User;

public class UserValidation implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName","firstName.empty" ,"must include first name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "lastName.empty", "must include last name");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.empty", "must include email");
		ValidationUtils.rejectIfEmpty(errors, "password", "password.empty", "must include password");
		
		User u = (User) target;
		
		if(u.getUserId() < 0) {
			errors.rejectValue("userId", "invalid.userId", "userId cannot be negative");
		}
		
		if(!(u.getEmail().contains("@") && u.getEmail().contains("."))) {
			errors.rejectValue("email", "invalid.email", "invalid email");
		}
		
		if(u.getPassword().length() < 8) {
			errors.rejectValue("password", "invalid.password", "passwords must contain at least 8 characters");
		}
	
	}

}
