package com.example.finalsecurity.Security;

import com.example.finalsecurity.Security.JwtToken.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyOnceFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

//        if( jwtUtil.isvalid(request.getHeader("token"))){
        if( request.getHeader("token").equals("yes")){
            System.out.println("the token is valid");
//            response.setHeader("token","this is the token value");

        }else {
            throw new BadCredentialsException("Token Not Found");
        }
        filterChain.doFilter(request, response);

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request)  {
//        System.out.println("-----------------------");
        return request.getRequestURI().contains("/addUser");
    }

}
