package com.springboot.app.springboot_crud.services;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.app.springboot_crud.models.User;
import com.springboot.app.springboot_crud.repositories.UserRepository;

import jakarta.transaction.Transactional;
@Service
public class JpaUserDetailsService implements UserDetailsService{
    @Autowired
    private UserRepository repository;
    
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Optional<User> userOptional = repository.findByUsername(username);

        if(userOptional.isEmpty()){
            throw new UsernameNotFoundException(String.format("Username %s no existe", username));
        }

        User user = userOptional.orElseThrow();

        Set<GrantedAuthority> authorities = user.getRoles().stream()
        .map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
        user.getPassword(),
        user.isEnable(),
        true,
        true,
        true,
        authorities);
    }

}
