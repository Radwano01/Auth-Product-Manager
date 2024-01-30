package com.example.ecommerce.Services;

import com.example.ecommerce.Dto.RegistrationDto.*;
import com.example.ecommerce.Entities.RoleEntity;
import com.example.ecommerce.Repository.RolesRepository;
import com.example.ecommerce.Repository.UserRepository;
import com.example.ecommerce.Entities.UserEntity;
import com.example.ecommerce.security.JWTAuthEntryPoint;
import com.example.ecommerce.security.JWTGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collections;

@Service
public class UserService {

    private final RolesRepository rolesRepository;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JWTGenerator jwtGenerator;

    @Autowired
    public UserService(UserRepository userRepository,
                       RolesRepository rolesRepository,
                       AuthenticationManager authenticationManager,
                       PasswordEncoder passwordEncoder,
                       JWTGenerator jwtGenerator){
        this.rolesRepository = rolesRepository;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }

    public ResponseEntity<AuthResponseDto> loginUser(LoginDto loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtGenerator.generateToken(authentication);
            AuthResponseDto authResponseDto = new AuthResponseDto(token);
            return new ResponseEntity<>(authResponseDto, HttpStatus.OK);
        }catch(Exception e){
            throw new AuthenticationCredentialsNotFoundException("Invalid email or token: "+e);
        }
    }

    public ResponseEntity<String> RegisterUser(RegisterDto registerDto) {
        if(userRepository.existsByEmail(registerDto.getEmail())){
            return new ResponseEntity<>("Email already taken!", HttpStatus.BAD_REQUEST);
        }else{
            try{
                UserEntity user = new UserEntity();
                user.setEmail(registerDto.getEmail());
                user.setUsername(registerDto.getUsername());
                user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
                RoleEntity role = rolesRepository.findByRole("USER")
                        .orElseThrow(() -> new RuntimeException("Role not found"));;
                user.setRoles(Collections.singletonList(role));
                userRepository.save(user);
                return new ResponseEntity<>("Account created", HttpStatus.OK);
            }catch (Exception e){
                throw new IllegalStateException("Error with creating new account: "+e);
            }
        }
    }

    public ResponseEntity<String> deleteUser(int id) {
        boolean exists = userRepository.existsById(id);
        if(!exists){
            return new ResponseEntity<>("Account id not found: "+id, HttpStatus.BAD_REQUEST);
        }else{
            try{
                userRepository.deleteById(id);
                rolesRepository.deleteById(id);
                return new ResponseEntity<>("Account deleted successfully", HttpStatus.OK);
            }catch(Exception e){
                throw new IllegalStateException("Something went wrong to delete try later! "+e);
            }

        }
    }

    @Transactional
    public ResponseEntity<String> editUser(EditDto editDto) {
        UserEntity user = userRepository.findUserByEmail(editDto.getEmail())
                .orElseThrow(() -> new IllegalStateException("User not found with email: " + editDto.getEmail()));
        if (editDto.getUsername() != null && !editDto.getUsername().isEmpty()) {
            user.setUsername(editDto.getUsername());
            return new ResponseEntity<>("Username updated successfully.", HttpStatus.OK);
        }
        if (editDto.getPassword() != null && !editDto.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(editDto.getPassword()));
            return new ResponseEntity<>("Password updated successfully", HttpStatus.OK);
        }

        return new ResponseEntity<>("Nothing updated", HttpStatus.BAD_REQUEST);
    }

}
