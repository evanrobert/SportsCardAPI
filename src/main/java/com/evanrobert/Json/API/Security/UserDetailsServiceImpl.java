package com.evanrobert.Json.API.Security;


import com.evanrobert.Json.API.Model.UserDetailService;
import com.evanrobert.Json.API.Repos.UserLoginDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
@Autowired
UserLoginDetailsRepo userRepository;
@Autowired
PasswordEncoder passwordEncoder;




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetailService user = userRepository.findByUsername(username);
        if (user != null) {
            return User.builder()
                    .username(user.getUsername())
                    .password(passwordEncoder.encode(user.getPassword()))
                    .authorities(Collections.emptyList())
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found: " + username);
        }
    }


}






