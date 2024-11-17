package com.springboot.app.springboot_crud.validation;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
@Component
public class ValidationRequired implements ConstraintValidator<IsRequired, String>{

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return StringUtils.hasText(value);
    }

}
