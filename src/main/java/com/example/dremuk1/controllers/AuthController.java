package com.example.dremuk1.controllers;

import com.example.dremuk1.mappers.UserMapper;
import com.example.dremuk1.models.User;
import com.example.dremuk1.services.AttendanceService;
import com.example.dremuk1.services.JwtCore;
import com.example.dremuk1.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final UserMapper userMapper;
    private final JwtCore jwtCore;
    public AuthController(UserService userService, UserMapper userMapper,JwtCore jwtCore) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.jwtCore = jwtCore;
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> signup(@RequestBody User input) {
        User user = userService.registerUser(input);
        return new ResponseEntity<>(userMapper.userToUserDTO(user), HttpStatus.CREATED);
    }
    @PostMapping("/signIn")
    public ResponseEntity<?> signIn(@RequestBody User input) {
        User user = userService.verify(input);
        return new ResponseEntity<>(jwtCore.generateToken(user), HttpStatus.OK);
    }
}