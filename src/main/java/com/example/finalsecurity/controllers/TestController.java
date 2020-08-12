package com.example.finalsecurity.controllers;

import com.example.finalsecurity.Security.JwtToken.JwtUtil;
import com.example.finalsecurity.zModel.UserReop;
import com.example.finalsecurity.zModel.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {
    @Autowired
    private UserReop userReop;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping(value = "/test" , method = RequestMethod.GET)
    public String test(){
        return "H  I";
    }

    @RequestMapping(value = "/postTest" , method = RequestMethod.POST)
    public String test3(){
        return "i am post request";
    }


    @RequestMapping(value = "/addUser" , method = RequestMethod.POST)
    public void addUser(@RequestBody Users user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userReop.save(user);
    }
    /******************************************************************/
    @RequestMapping(value = "/login" , method = RequestMethod.POST)
    public String login(){
//        System.out.println(jwtUtil.generateToken());
        return jwtUtil.generateToken();
    }

}
