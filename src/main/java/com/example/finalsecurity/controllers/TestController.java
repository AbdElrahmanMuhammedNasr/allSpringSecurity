package com.example.finalsecurity.controllers;

import com.example.finalsecurity.zModel.UserReop;
import com.example.finalsecurity.zModel.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {
    @Autowired
    private UserReop userReop;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/test" , method = RequestMethod.GET)
    public String test(){
        return "H  I";
    }

    @RequestMapping(value = "/addUser" , method = RequestMethod.POST)
    public void addUser(@RequestBody Users user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userReop.save(user);
    }

}
