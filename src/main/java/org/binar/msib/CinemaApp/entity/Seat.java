package org.binar.msib.CinemaApp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@IdClass(SeatPKId.class)
public class Seat {

    @Id
    private Integer SeatNum;
    @Id
    private Integer studio_id;
    @Column
    private boolean seat_status;
    @Column
    private String SeatRow;

}
