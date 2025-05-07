package com.example.CinemaTicketServer.Controller;

import com.example.CinemaTicketServer.Model.Movie;
import com.example.CinemaTicketServer.MovieApi;
import com.example.CinemaTicketServer.Service.MovieService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private MovieApi movieApi;
    @Autowired
    private ObjectMapper objectMapper;

    @CrossOrigin(origins = "http://localhost:63342")
    @GetMapping("/get")
    @ResponseBody
    public List<Movie> getMovie(@RequestParam String title){
        System.out.println("Querying: "+title);
        return movieService.getMovie(title);
    }

    @PostMapping("/fetch")
    @ResponseBody
    public List<Movie> fetchMovie(@RequestParam String title) throws JsonProcessingException{
        System.out.println("Fetching: " +title);
        return movieService.fetchMovie(title);

    }

    @GetMapping("/getPoster")
    @ResponseBody
    public ArrayList<String> getPoster(@RequestParam String title) throws JsonProcessingException {
        return movieService.getMoviePosterArt(title);
    }
}
