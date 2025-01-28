package com.scheduling.scheduling_app.controller;

import com.scheduling.scheduling_app.dto.UserDto;
import com.scheduling.scheduling_app.entity.User;
import com.scheduling.scheduling_app.service.PasswordService;
import com.scheduling.scheduling_app.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private PasswordService passwordService;

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
        this.passwordService =  new PasswordService();
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }


    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserDto userDto) {
        String encryptedPassword = passwordService.encryptPassword(userDto.getPassword());
        User user = userDto.convertUserDto(userDto);
        user.setPassword(encryptedPassword);
        userService.createUser(user);

        return ResponseEntity.ok("Usuário criado com sucesso!");
    }

}
