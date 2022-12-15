package org.binar.msib.CinemaApp.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer role_id;

    @Column(length = 20)
    private String roleName;

    @JsonIgnore
    @ManyToMany(mappedBy = "rolesUsers", cascade = CascadeType.ALL)
    private Set<User> users = new HashSet<>();
}
