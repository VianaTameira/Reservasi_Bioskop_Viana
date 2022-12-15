package org.binar.msib.CinemaApp.services.impl;

import org.binar.msib.CinemaApp.dto.ScheduleRequest;
import org.binar.msib.CinemaApp.dto.ScheduleResponse;
import org.binar.msib.CinemaApp.entity.Film;
import org.binar.msib.CinemaApp.entity.Schedule;
import org.binar.msib.CinemaApp.repository.FilmRepository;
import org.binar.msib.CinemaApp.repository.ScheduleRepository;
import org.binar.msib.CinemaApp.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private FilmRepository filmRepository;

    @Override
    public ScheduleResponse addSchedule(ScheduleRequest scheduleRequest) {
        try {
            Optional<Film> film = filmRepository.findById(scheduleRequest.getFilm_code());
            if (film.isPresent())
            {
                Schedule schedule = scheduleRequest.toSchedule(film.get());
                try {
                    scheduleRepository.save(schedule);
                    return ScheduleResponse.build(schedule);
                }
                catch (Exception exception)
                {
                    return null;
                }
            }
            else
                return null;
        }
        catch (Exception exception)
        {
            return null;
        }
    }

    @Override
    public List<ScheduleResponse> searchFilmSchedule(Integer film_code) {
        List<Schedule> allSchedule = scheduleRepository.findAllScheduleByFilmCode(film_code);
        List<ScheduleResponse> allScheduleResponse = new ArrayList<>();
        for (Schedule schedule : allSchedule)
        {
            ScheduleResponse scheduleResponse = ScheduleResponse.build(schedule);
            allScheduleResponse.add(scheduleResponse);
        }
        return allScheduleResponse;
    }
}
