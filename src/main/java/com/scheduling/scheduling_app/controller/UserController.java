package com.scheduling.scheduling_app.controller;

import com.scheduling.scheduling_app.dto.LoginResponse;
import com.scheduling.scheduling_app.dto.UserDto;
import com.scheduling.scheduling_app.entity.User;
import com.scheduling.scheduling_app.service.JwtService;
import com.scheduling.scheduling_app.service.PasswordService;
import com.scheduling.scheduling_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("User")
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;


    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }


    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @PostMapping
    public ResponseEntity<LoginResponse> createUser(@RequestBody UserDto userDto) {
        try {
        String encryptedPassword = passwordService.encryptPassword(userDto.getPassword());
        User user = userDto.convertUserDto(userDto);
        user.setPassword(encryptedPassword);
        userService.createUser(user);


            String token = jwtService.generateToken(user);

            return ResponseEntity.ok(new LoginResponse(token));

        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUser(@RequestHeader("Authorization") String token) {
        try {
            String jwt = token.startsWith("Bearer ") ? token.substring(7) : token;

            String userEmail = jwtService.extractEmail()
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
