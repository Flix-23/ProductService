package com.springboot.app.springboot_crud.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = IsExistDbValidation.class)
@Retention(RetentionPolicy.RUNTIME)
@Target( {ElementType.FIELD, ElementType.METHOD})
public @interface IsExistDb {
    
     String message() default "ya existe en la base de datos";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
