package com.springboot.app.springboot_crud.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.springboot.app.springboot_crud.models.User;

public interface UserRepository extends CrudRepository<User, Long>{
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
}
