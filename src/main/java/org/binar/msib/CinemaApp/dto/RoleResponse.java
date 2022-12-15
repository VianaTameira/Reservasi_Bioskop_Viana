package org.binar.msib.CinemaApp.dto;

import lombok.Builder;
import lombok.Data;
import org.binar.msib.CinemaApp.entity.Role;

@Data
@Builder
public class RoleResponse {
    private Integer role_Id;
    private String name;

    public static RoleResponse build(Role role) {
        return RoleResponse.builder()
                .role_Id(role.getRole_id())
                .name(String.valueOf(role.getRoleName()))
                .build();
    }
}
