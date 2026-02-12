package com.divyanshu.user_service.user_service_service;

import com.divyanshu.user_service.user_service_model.Role;
import com.divyanshu.user_service.user_service_repository.UserRepository;
import com.divyanshu.user_service.user_service_security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.divyanshu.user_service.user_service_model.User;


@Service
public class AuthService {


    @Autowired
    private UserRepository repo;

    @Autowired
    private JwtUtil util;

    @Autowired
    private PasswordEncoder encoder;

    public String signup(User user){

        // check if email is already exits
        if (repo.findByEmail(user.getEmail()).isPresent()){
            return "Email already registered ";
        }

        // Encrypt Password
        user.setPassword(encoder.encode(user.getPassword()));

        //  default role
        if (user.getRole()==null){
            user.setRole(Role.CUSTOMER);
        }

        // Save user
        repo.save(user);

        return "User registered successfully";
    }

    public String login(String email , String password){

        User user=repo.findByEmail(email).orElseThrow(() -> new RuntimeException("Invalid email or password "));

        if (!encoder.matches(password , user.getPassword())){
            throw new RuntimeException("Invalid email or password");
        }
        return util.generateToken(user.getEmail());
    }
}
