package com.springboot.app.springboot_crud.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.app.springboot_crud.models.Role;
import com.springboot.app.springboot_crud.models.User;
import com.springboot.app.springboot_crud.repositories.UserRepository;
import com.springboot.app.springboot_crud.repositories.RoleRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    @Autowired 
    private RoleRepository roleRepository;

    @Autowired 
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return (List<User>) repository.findAll();
    }

    @Override
    @Transactional
    public User create(User user) {
        Optional<Role> optionalRoleUser = roleRepository.findByName("ROLE_USER");
        Set<Role> roles = new HashSet<>();

        optionalRoleUser.ifPresent(roles::add);
        
        if(user.isAdmin()){
            Optional<Role> optionalRoleAdmin = roleRepository.findByName("ROLE_ADMIN");
            optionalRoleAdmin.ifPresent(roles::add);
        }
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    @Override
    public boolean existsByUsername(String username) {
       return repository.existsByUsername(username);
    }

}
