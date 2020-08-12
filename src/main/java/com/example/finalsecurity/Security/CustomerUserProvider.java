package com.example.finalsecurity.Security;

import com.example.finalsecurity.Security.JwtToken.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomerUserProvider implements AuthenticationProvider {
    @Autowired
    private MyDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password =String.valueOf(authentication.getCredentials());
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (userDetails!=null){

                if(passwordEncoder.matches(password, userDetails.getPassword())){
                    var a = new UsernamePasswordAuthenticationToken(username, password,userDetails.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(a);

                    System.out.println("--------------------------------------------");
                    System.out.println(SecurityContextHolder.getContext());
                    System.out.println("--------------------------------------------");
//                    System.out.println(jwtUtil.generateToken());
                    System.out.println("--------------------------------------------");
                    return a;
                }
        }

        throw new BadCredentialsException("Not Auth");
    }

    @Override
    public boolean supports(Class<?> authType) {
        return UsernamePasswordAuthenticationToken.class.equals(authType);
    }
}
