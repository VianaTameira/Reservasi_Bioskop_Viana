package org.binar.msib.CinemaApp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.binar.msib.CinemaApp.entity.User;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SignupResponse {

        private Long id;
        private String username;
        private String email;

        public static SignupResponse build(User user) {
            return SignupResponse.builder()
                    .id(user.getId())
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .build();
        }
}
