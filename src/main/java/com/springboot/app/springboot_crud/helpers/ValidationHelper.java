package com.springboot.app.springboot_crud.helpers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public class ValidationHelper {
    
    public ResponseEntity<?> validationError(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
 
        result.getFieldErrors().forEach(error -> {
         errors.put(error.getField(), "El campo " + error.getField() + " " + error.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
     }
}
