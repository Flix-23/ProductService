package com.springboot.app.springboot_crud.services;

import java.util.List;

import com.springboot.app.springboot_crud.models.User;

public interface UserService {

    List<User> findAll();
    User create(User user);
    boolean existsByUsername(String username);

}
