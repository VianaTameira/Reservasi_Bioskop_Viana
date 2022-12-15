package org.binar.msib.CinemaApp.dto;

import lombok.Data;
import org.binar.msib.CinemaApp.entity.Film;
import org.binar.msib.CinemaApp.entity.Schedule;

import javax.validation.constraints.NotEmpty;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ScheduleRequest {
    @NotEmpty(message = "Show Date is required.")
    private LocalDate tanggal_tayang;
    @NotEmpty(message = "Start time is required.")
    private LocalTime jam_mulai;
    @NotEmpty(message = "End time is required.")
    private LocalTime jam_selesai;
    @NotEmpty(message = "Film code is required.")
    private Integer film_code;

    public Schedule toSchedule(Film film) {
        Schedule schedule = new Schedule();
        schedule.setTanggal_tayang(this.tanggal_tayang);
        schedule.setJam_mulai(this.jam_mulai);
        schedule.setJam_selesai(this.jam_selesai);
        schedule.setFilm_code(this.film_code);
        return schedule;
    }
}
