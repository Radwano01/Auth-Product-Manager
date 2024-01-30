package com.example.ecommerce.security;

import com.example.ecommerce.Entities.RoleEntity;
import com.example.ecommerce.Entities.UserEntity;
import com.example.ecommerce.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    @Autowired
    public CustomUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        UserEntity user = userRepository.findUserByUsername(username).orElseThrow(()-> new UsernameNotFoundException("Username not found"));
            return new CustomUserDetails(user.getId(), user.getUsername(),user.getEmail(), user.getPassword(), mapAuthority(user.getRoles()));
    }

    private Collection<GrantedAuthority> mapAuthority(List<RoleEntity> roles){
        return roles.stream().map(role->new SimpleGrantedAuthority(role.getRoles())).collect(Collectors.toList());
    }

}
