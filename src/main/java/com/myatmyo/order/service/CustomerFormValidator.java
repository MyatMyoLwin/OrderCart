package com.myatmyo.order.service;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.myatmyo.order.model.Customer;


public class CustomerFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> paramClass) {
		return Customer.class.equals(paramClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		
		Customer customer = (Customer) obj;		
				
		if((customer.getAddress()).length() < 5 && (customer.getAddress()).length() > 0){
			//errors.rejectValue("price", "negativeValue", new Object[]{"'price'"}, "price can't be negative.");
			errors.rejectValue("address", "address.short");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "address.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "username.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required");
		
	}
}