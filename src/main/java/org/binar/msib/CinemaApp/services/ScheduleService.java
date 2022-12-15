package org.binar.msib.CinemaApp.services;

import org.binar.msib.CinemaApp.dto.ScheduleRequest;
import org.binar.msib.CinemaApp.dto.ScheduleResponse;

import java.util.List;

public interface ScheduleService {
    ScheduleResponse addSchedule(ScheduleRequest scheduleRequest);
    List<ScheduleResponse> searchFilmSchedule(Integer film_code);
}
