package org.binar.msib.CinemaApp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class EntityUser{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer user_id;

    @Column
    private String username;
    @Column
    private String email;
    @Column
    private String password;
    @OneToMany(targetEntity = Orders.class, cascade = CascadeType.ALL)
    private List<Orders> orders;

}
