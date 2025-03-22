package com.bfst.titrefoncier.user;

import jakarta.faces.view.ViewScoped;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
@Component("userOwnerView")
@Getter
@Setter
@ViewScoped
public class UserOwnerController {
    @Autowired
    private UserService userService;
    private User user;

    public List<User> getUserByOrderByDateCreatedDesc(){
        return userService.getUserByOrderByDateCreatedDesc();
    }
}
