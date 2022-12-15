package org.binar.msib.CinemaApp.dto;

import lombok.Builder;
import lombok.Data;
import org.binar.msib.CinemaApp.entity.Schedule;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class ScheduleResponse {
    private Integer schedule_Id;
    private LocalDate tanggal_tayang;
    private LocalTime jam_mulai;
    private LocalTime jam_selesai;
    private Integer film_code;

    public static ScheduleResponse build(Schedule schedules) {
        return ScheduleResponse.builder()
                .schedule_Id(schedules.getSchedule_id())
                .tanggal_tayang(schedules.getTanggal_tayang())
                .jam_mulai(schedules.getJam_mulai())
                .jam_selesai(schedules.getJam_selesai())
                .film_code(schedules.getFilm_code())
                .build();
    }
}
