package org.binar.msib.CinemaApp.dto;

import lombok.Data;
import org.binar.msib.CinemaApp.entity.Role;

import javax.validation.constraints.NotEmpty;

@Data
public class RoleRequest {
    @NotEmpty(message = "Role name is required.")
    private String roleName;

    public Role toRoles() {
        return Role.builder()
                .roleName(this.roleName)
                .build();
    }
}
