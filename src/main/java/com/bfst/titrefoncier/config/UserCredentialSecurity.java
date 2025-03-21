package com.bfst.titrefoncier.config;


import com.bfst.allocation.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserCredentialSecurity implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        com.bfst.allocation.user.User user = userRepository.findByUsername(username).orElse(null);
        if(user != null && user.getRole() != null &&
                user.isEnabled()){
            grantedAuthorities.add(
                    new SimpleGrantedAuthority("ROL_"+ user.getRole()));

            return new User(
                    user.getUsername(),
                    user.getPassword(),
                    user.isEnabled(),
                    true,
                    true,
                    true,
                    grantedAuthorities
            );
        }
       return null;
    }
}
