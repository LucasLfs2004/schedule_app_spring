package com.scheduling.scheduling_app.controller;

import com.scheduling.scheduling_app.dto.AuthDto;
import com.scheduling.scheduling_app.dto.LoginResponse;
import com.scheduling.scheduling_app.dto.UserDto;
import com.scheduling.scheduling_app.entity.User;
import com.scheduling.scheduling_app.repository.UserRepository;
import com.scheduling.scheduling_app.service.JwtService;
import com.scheduling.scheduling_app.service.PasswordService;
import com.scheduling.scheduling_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("Login")
@RequestMapping("api/auth")
public class AuthController {

    private PasswordService passwordService;

    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;


    public AuthController(UserService userService) {
        this.userService = userService;
        this.passwordService =  new PasswordService();
    }

    @PostMapping
    public ResponseEntity<LoginResponse> loginUser(@RequestBody AuthDto authDto) {
        User user = userRepository.findByEmail(authDto.getEmail());

        if (user == null) {
            return ResponseEntity.status(401).body(new LoginResponse("Usuário não encontrado."));
        } else if(!passwordService.checkPassword(authDto.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).body(new LoginResponse("Credenciais inválidas!"));
        }
        // Gerar o token JWT
        String token = jwtService.generateToken(user);

        return ResponseEntity.ok(new LoginResponse(token));
    }
}
