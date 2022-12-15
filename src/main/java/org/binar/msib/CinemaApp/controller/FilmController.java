package org.binar.msib.CinemaApp.controller;

import org.binar.msib.CinemaApp.dto.FilmDTO;
import org.binar.msib.CinemaApp.dto.MessageResponse;
import org.binar.msib.CinemaApp.entity.Film;
import org.binar.msib.CinemaApp.entity.Schedule;
import org.binar.msib.CinemaApp.services.FilmService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/film")
public class FilmController {
    final
    FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MessageResponse> addFilm(@RequestBody FilmDTO request) {
        MessageResponse messageResponse = new MessageResponse();
        Film film = filmService.mapToEntity(request);
        Film result = filmService.insertFilm(film);
        if (result == null) {
            messageResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            messageResponse.setMessage("Failed to add film");
            return ResponseEntity.badRequest().body(messageResponse);
        } else {
            messageResponse.setStatus(HttpStatus.CREATED.value());
            messageResponse.setMessage("Add new film");
            messageResponse.setData(result);
            return ResponseEntity.ok().body(messageResponse);
        }
    }

    @PutMapping("/update/{film_code}")
    public ResponseEntity<MessageResponse> updateAirplane(@PathVariable Integer film_code, @RequestBody FilmDTO request) {
        MessageResponse messageResponse = new MessageResponse();
        Film film = filmService.mapToEntity(request);
        Film result = filmService.updateFilm(film_code, film);
        if (result == null) {
            messageResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            messageResponse.setMessage("Failed to update film");
            return ResponseEntity.badRequest().body(messageResponse);
        } else {
            messageResponse.setStatus(HttpStatus.CREATED.value());
            messageResponse.setMessage("Add new film");
            messageResponse.setData(result);
            return ResponseEntity.ok().body(messageResponse);
        }
    }
    @GetMapping("/get/on-showing")
    public ResponseEntity<MessageResponse> getOnshowingFilm() {
        MessageResponse messageResponse = new MessageResponse();
        try {
            Film film = new Film();
            Boolean sedangTayang = film.getSedangTayang();
            List<Film> filmGet = filmService.findOnShowingFilm(sedangTayang);
            messageResponse.setMessage("Success get on showing film");
            messageResponse.setStatus(HttpStatus.OK.value());
            messageResponse.setData(filmGet);
            return ResponseEntity.ok().body(messageResponse);
        }catch (Exception exception)
        {
            messageResponse.setMessage("Failed get on showing film");
            messageResponse.setStatus(HttpStatus.BAD_GATEWAY.value());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(messageResponse);
        }
    }
   @GetMapping("/all")
   public ResponseEntity<MessageResponse> getAllFilm(){
       MessageResponse messageResponse = new MessageResponse();
       try {
           List<Film> filmGet = filmService.getAllFilm();
           messageResponse.setMessage("Success get on showing film");
           messageResponse.setStatus(HttpStatus.OK.value());
           messageResponse.setData(filmGet);
           return ResponseEntity.ok().body(messageResponse);
       }catch (Exception exception)
       {
           messageResponse.setMessage("Failed get on showing film");
           messageResponse.setStatus(HttpStatus.BAD_GATEWAY.value());
           return ResponseEntity.status(HttpStatus.BAD_GATEWAY.value()).body(messageResponse);
       }
   }
   @DeleteMapping("/delete/{film_code}")
   public ResponseEntity<MessageResponse> deleteFilm(@PathVariable Integer film_code)
   {
       MessageResponse messageResponse = new MessageResponse();
       Boolean deleteFilm = filmService.delete(film_code);
       if(deleteFilm)
       {
           messageResponse.setMessage("Success delete film by code: " + film_code);
           messageResponse.setStatus(HttpStatus.OK.value());
           return ResponseEntity.ok().body(messageResponse);
       }
       else
       {
           messageResponse.setMessage("Failed delete film by code: " + film_code + ", not found");
           messageResponse.setStatus(HttpStatus.BAD_REQUEST.value());
           return ResponseEntity.badRequest().body(messageResponse);
       }
   }

   @GetMapping("/getschedule/{film_code}")
   public Schedule getSchedule(@PathVariable Integer film_code){
       return filmService.getSchedule(film_code);
   }
}
