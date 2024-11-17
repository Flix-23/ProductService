package com.springboot.app.springboot_crud.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.springboot_crud.helpers.ValidationHelper;
import com.springboot.app.springboot_crud.models.User;
import com.springboot.app.springboot_crud.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins="http://localhost:4200", originPatterns = "*")
public class UserController extends ValidationHelper{
    @Autowired
    private UserService service;

    @GetMapping
    public List<User> findAll(){
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody User user, BindingResult result){
        if(result.hasFieldErrors()){
            return validationError(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(user));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user, BindingResult result){
        
        user.setAdmin(false);
        return create(user, result);
    }
}
