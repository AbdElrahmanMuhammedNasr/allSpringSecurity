package com.example.finalsecurity.Security;

import com.example.finalsecurity.Security.Filters.MyOnceFilter;
import com.example.finalsecurity.Security.Providers.CustomerUserProvider;
import com.example.finalsecurity.Security.UserServiceAndDetails.MyDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyDetailsService myDetailsService;
    @Autowired
    private CustomerUserProvider customerUserProvider;
    @Autowired
    private MyOnceFilter myOnceFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .csrf(c->{
//                    c.csrfTokenRepository(new CustomCsrfToken());
//                })
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/addUser").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(myOnceFilter, BasicAuthenticationFilter.class)
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(myDetailsService).passwordEncoder(passwordEncoder());
        auth.authenticationProvider(customerUserProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



}
