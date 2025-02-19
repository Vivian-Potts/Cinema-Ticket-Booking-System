package com.example.CinemaTicketServer.Controller;

import com.example.CinemaTicketServer.Model.Movie;
import com.example.CinemaTicketServer.MovieApi;
import com.example.CinemaTicketServer.Service.MovieService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class Controller {

    @Autowired
    private MovieService movieService;
    @Autowired
    private MovieApi movieApi;
    @Autowired
    private ObjectMapper objectMapper;


    @GetMapping("/get")
    @ResponseBody
    public List<Movie> showExampleMovie(@RequestParam String title) throws JsonProcessingException {
        System.out.println("Querying: "+title);
        return movieService.getMovie(title);
    }
}
