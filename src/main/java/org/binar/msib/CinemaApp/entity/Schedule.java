package org.binar.msib.CinemaApp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer schedule_id;

    @Column
    private Integer film_code;
    @ManyToOne
    @JoinColumn(name = "film_code", nullable = false, insertable = false, updatable = false)
    private Film film;

    @Column
    private LocalDate tanggal_tayang;

    @JsonFormat(pattern = "HH:mm:ss")
    @Column
    private LocalTime jam_mulai;

    @JsonFormat(pattern = "HH:mm:ss")
    @Column
    private LocalTime jam_selesai;
}
