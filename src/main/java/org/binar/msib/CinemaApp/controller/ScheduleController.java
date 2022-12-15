package org.binar.msib.CinemaApp.controller;

import org.binar.msib.CinemaApp.dto.FilmDTO;
import org.binar.msib.CinemaApp.dto.MessageResponse;
import org.binar.msib.CinemaApp.dto.ScheduleRequest;
import org.binar.msib.CinemaApp.dto.ScheduleResponse;
import org.binar.msib.CinemaApp.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MessageResponse> addSchedule(@RequestBody ScheduleRequest scheduleRequest) {
        MessageResponse messageResponse = new MessageResponse();
        ScheduleResponse scheduleResponse = scheduleService.addSchedule(scheduleRequest);
        if (scheduleResponse == null) {
            messageResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            messageResponse.setMessage("Failed to add schedule");
            return ResponseEntity.badRequest().body(messageResponse);
        } else {
            messageResponse.setStatus(HttpStatus.CREATED.value());
            messageResponse.setMessage("Add new schedule");
            messageResponse.setData(scheduleResponse);
            return ResponseEntity.ok().body(messageResponse);
        }
    }
    @GetMapping("/{film_code}")
    public ResponseEntity<MessageResponse> getAirplaneSeat(@PathVariable Integer film_code){
        MessageResponse messageResponse = new MessageResponse();
        try {
            List<ScheduleResponse> scheduleResponses = scheduleService.searchFilmSchedule(film_code);
            messageResponse.setMessage("Success get schedule by film code : " + film_code);
            messageResponse.setStatus(HttpStatus.OK.value());
            messageResponse.setData(scheduleResponses);
            return ResponseEntity.ok().body(messageResponse);
        }catch (Exception exception)
        {
            messageResponse.setMessage("Failed get schedule by film code, " + film_code + " not found");
            messageResponse.setStatus(HttpStatus.BAD_GATEWAY.value());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(messageResponse);
        }
    }
}
