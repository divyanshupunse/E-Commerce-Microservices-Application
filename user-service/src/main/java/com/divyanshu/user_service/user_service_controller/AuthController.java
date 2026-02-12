package com.divyanshu.user_service.user_service_controller;

import com.divyanshu.user_service.user_service_model.User;
import com.divyanshu.user_service.user_service_service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService service;


    @PostMapping("/register")
    public String signup(@RequestBody User user){
         service.signup(user);
         return "User Registered Successfully";
    }

    @PostMapping("/login")
    public  String login(@RequestParam String email , @RequestParam String password){

        String token=service.login(email, password);
        return token;
    }




}
