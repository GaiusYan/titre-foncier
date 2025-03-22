package com.bfst.titrefoncier.authentication;

import com.bfst.titrefoncier.user.Role;
import com.bfst.titrefoncier.user.User;
import com.bfst.titrefoncier.user.UserService;
import jakarta.faces.annotation.View;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component("registerView")
@Getter
@Setter
@ViewScoped
public class RegisterController {

    @Autowired
    private UserService userService;
    private User user = new User();
    private String confirmPassword;

    public void register() {
        if (Objects.nonNull(getUserByUsername(user.getUsername())))
            return;

        if (!this.isConfirmPasswordValid(user)){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Information", "Les mots de passe ne correspondent pas");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
        }

        user.setRole(Role.DEMANDEUR);
        userService.createUser(user);
        user = new User();

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "Votre compte a été créé avec succès");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    private User getUserByUsername(String username) {
        User user = this.userService.getUserByUsername(username);
        if (Objects.nonNull(user)) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Information", "Cet utilisateur existe déjà");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return user;
        }
        return null;
    }

    private boolean isConfirmPasswordValid(User user) {
        return user.getPassword().equals(this.confirmPassword);
    }
}
