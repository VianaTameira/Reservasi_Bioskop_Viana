package org.binar.msib.CinemaApp.dto;

import lombok.Data;
import org.binar.msib.CinemaApp.entity.User;

import javax.validation.constraints.NotEmpty;

@Data
public class SignupRequest {
    @NotEmpty(message = "username is required.")
    private String username;
    @NotEmpty(message = "email is required.")
    private String email;
    @NotEmpty(message = "password is required.")
    private String password;

    @NotEmpty(message = "rolesUsers is required.")
    private String roleName;

    public User toUsers() {
        User user = new User();
        user.setUsername(this.username);
        user.setEmail(this.email);
        user.setPassword(this.password);
        return user;
    }
}
