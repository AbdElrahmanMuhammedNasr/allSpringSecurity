package com.example.finalsecurity.controllers;

import com.example.finalsecurity.zModel.UserReop;
import com.example.finalsecurity.zModel.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {
    @Autowired
    private UserReop userReop;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/test" , method = RequestMethod.GET)
    public String test(  @RequestHeader("Authorization") String Data ){
        return "H  I";
    }

    @RequestMapping(value = "/addUser" , method = RequestMethod.POST)
    public void addUser(@RequestBody Users user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userReop.save(user);
    }

}
