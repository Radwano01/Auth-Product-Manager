package com.example.ecommerce.Controllers;
import com.example.ecommerce.Dto.RegistrationDto.*;
import com.example.ecommerce.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path="/api/v1/user")
public class UserController {

    private final UserService userService;
    @Autowired
    private UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/login")
    public ResponseEntity<AuthResponseDto> loginUser(@RequestBody LoginDto loginDto) {
        return userService.loginUser(loginDto);
    }

    @PostMapping("/register")
    public ResponseEntity<String> RegisterUser(@RequestBody RegisterDto registerDto){
        return userService.RegisterUser(registerDto);
    }

    @DeleteMapping(path="/delete/{id}")
    public ResponseEntity<String> removeUser(@PathVariable("id") int id){
        return userService.deleteUser(id);
    }

    @PutMapping(path="/edit/{id}")
    public ResponseEntity<String> updateUser(@PathVariable("id") int id,
                           @RequestParam(required = false) String email,
                           @RequestParam(required = false) String username,
                           @RequestParam(required = false) String password,@RequestBody EditDto editDto){
        return userService.editUser(editDto);

    }
}
