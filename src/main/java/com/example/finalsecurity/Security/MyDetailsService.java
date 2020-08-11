package com.example.finalsecurity.Security;

import com.example.finalsecurity.zModel.UserReop;
import com.example.finalsecurity.zModel.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyDetailsService implements UserDetailsService {

    @Autowired
    private UserReop userReop;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Users user = userReop.findByUserName(s);
        if(user !=null){
            return new MyUserDetails(user);
        }else {
            return (UserDetails) new UsernameNotFoundException("User Not Found");
        }
    }


}
