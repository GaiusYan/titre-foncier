package com.bfst.titrefoncier.user;

import jakarta.faces.view.ViewScoped;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Objects;


@Component("userView")
@Getter
@Setter
@ViewScoped
public class UserController {

    @Autowired
    private UserService userService;
    private User user;

    public List<User> getAllUser(){
        return userService.getAllUser();
    }

    public void createUserAccount(){
    }

    public void createOrUpdateUserAccount(User user){
        if(Objects.isNull(user.getId()))
            this.userService.createUser(user);
        else
            this.userService.updateUser(user.getId(),user);
    }


}
