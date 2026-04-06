package com.Apps.quantitymeasurement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Apps.quantitymeasurement.dto.LoginDTO;
import com.Apps.quantitymeasurement.dto.RegisterDTO;
import com.Apps.quantitymeasurement.entity.User;
import com.Apps.quantitymeasurement.repository.UserRepository;
import com.Apps.quantitymeasurement.security.JwtUtil;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    //  REGISTER (LOCAL USER)
    public String register(RegisterDTO dto) {

        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            return "User already exists";
        }

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setProvider("LOCAL"); //  IMPORTANT

        userRepository.save(user);

        return "User Registered Successfully";
    }

    // LOGIN (LOCAL USER)
    public String login(LoginDTO dto) {

        User user = userRepository.findByEmail(dto.getEmail()).orElse(null);

        if (user == null) {
            return "User not found";
        }

        //  IMPORTANT: prevent Google users from logging via password
        if ("GOOGLE".equals(user.getProvider())) {
            return "Please login using Google";
        }

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            return "Invalid password";
        }

        return jwtUtil.generateToken(user.getEmail());
    }
}