package com.example.ecommerce.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUserDetails extends User{

    private final int id;
    private final String email;
    public CustomUserDetails(Integer id, String username,
                             String email, String password,
                             Collection<? extends GrantedAuthority> authorities){
        super(username,password,authorities);
        this.id = id;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}