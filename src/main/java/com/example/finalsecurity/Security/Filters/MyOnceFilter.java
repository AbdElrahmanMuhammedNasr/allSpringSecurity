package com.example.finalsecurity.Security.Filters;

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

            if(request.getRequestURI().contains("/login")){
                filterChain.doFilter(request, response);
            }
            else if( jwtUtil.isvalid(request.getHeader("token").substring("Bearer ".length()))){
                 System.out.println("the token is valid");
                filterChain.doFilter(request, response);

            }else {
                System.out.println("Error is occur");
                throw new BadCredentialsException("The Token is Not valid");
            }

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request)  {
//        System.out.println("-----------------------");
            return request.getRequestURI().contains("/addUser") ;
    }

}
