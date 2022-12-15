package org.binar.msib.CinemaApp.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer order_id;

    @Column
    private Integer film_code;
    @ManyToOne
    @JoinColumn(name = "film_code", nullable = false, insertable = false, updatable = false)
    private Film filmOrder;

    @Column
    private Long user_id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
    private User userOrder;

    @Column
    private String SeatNum;

    @Column
    private Integer studio_id;

    @ManyToOne
    @JoinColumn(name = "SeatNum", nullable = false, insertable = false, updatable = false)
    @JoinColumn (name = "studio_id", nullable = false, insertable = false, updatable = false)
    private Seat seatOrder;

    @Column
    private Integer jumlah;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

}
