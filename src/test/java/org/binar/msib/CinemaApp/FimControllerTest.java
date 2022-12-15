package org.binar.msib.CinemaApp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.binar.msib.CinemaApp.controller.FilmController;
import org.binar.msib.CinemaApp.dto.FilmDTO;
import org.binar.msib.CinemaApp.entity.Film;
import org.binar.msib.CinemaApp.repository.UserRepository;
import org.binar.msib.CinemaApp.services.FilmService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FilmController.class)
public class FimControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    public ObjectMapper objectMapper;

    @MockBean
    private FilmService filmService;

    @MockBean
    private FilmDTO filmDTO;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private Film film;

    @Test
    public void testGetAllFilm() throws Exception {
        List<FilmDTO> listFilm = new ArrayList<FilmDTO>();
        listFilm.add(new FilmDTO(1, "Pengabdi Mantan", true, 80000));
        listFilm.add(new FilmDTO(2, "Terlalu Handsome", true, 80000));

        Mockito.when(filmService.getAllFilm().stream().map(filmService :: mapToDto).collect(Collectors.toList())).thenReturn(listFilm);

        String url = "/all";

        MvcResult mvcResult = mockMvc.perform(get(url)).andExpect(status().isOk()).andReturn();

        String actualJsonResponse = mvcResult.getResponse().getContentAsString();
        System.out.println(actualJsonResponse);
        String expectedJsonResponse = objectMapper.writeValueAsString(listFilm);
        assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(expectedJsonResponse);
    }

    @Test
    public void testUpdateFilm() throws Exception{
        Mockito.when(filmService.mapToDto(filmService.updateFilm(filmDTO.film_code(), film)));
        String url ="/update/{film_code";
        mockMvc.perform(get(url)).andExpect(status().isOk());
    }

    @Test
    public void testInsertFilm() throws Exception {
        FilmDTO savedFilm = new FilmDTO(1,"Pengabdi Mantan",true,80000);
        Mockito.when(filmService.mapToDto(filmService.insertFilm(film))).thenReturn(savedFilm);
        String url ="/create";
        mockMvc.perform(get(url)).andExpect(status().isOk());
    }

    @Test
    public void testDeleteFilm() throws Exception {
        Mockito.when(filmService.delete(filmDTO.film_code()));
        String url = "/delete/{film_code}";
        mockMvc.perform(get(url)).andExpect(status().isOk());
    }

    @Test
    public void testGetSchedule() throws Exception{
        Mockito.when(filmService.getSchedule(filmDTO.film_code()));
        String url = "/getschedule/{film_code}";
        mockMvc.perform(get(url)).andExpect(status().isOk());
    }

}
