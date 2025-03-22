package com.bfst.titrefoncier.authentication;

import com.bfst.titrefoncier.user.User;
import com.bfst.titrefoncier.user.UserService;
import jakarta.faces.view.ViewScoped;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component("authenticationView")
@Getter
@Setter
@ViewScoped
public class AuthenticationController {

    @Autowired
    private UserService userService;

    public User getCurrentUser() {
        Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (object instanceof UserDetails) {
            return userService.getUserByUsername(((UserDetails) object).getUsername());
        }
        throw new UsernameNotFoundException("Aucun utilisateur connecté");
    }

}
