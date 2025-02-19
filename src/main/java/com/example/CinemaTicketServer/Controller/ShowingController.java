package com.example.CinemaTicketServer.Controller;


import com.example.CinemaTicketServer.Model.Movie;
import com.example.CinemaTicketServer.Model.Showing;
import com.example.CinemaTicketServer.MovieApi;
import com.example.CinemaTicketServer.Service.ShowingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShowingController {

    @Autowired
    private ShowingService showingService;

    @Autowired
    private MovieApi movieApi;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/showings")
    public List<Showing> getShowings() {
        return showingService.getShowings();
    }

    @GetMapping("/movie/{movieName}")
    public List<Showing> getShowingMovies(@PathVariable String movieName) {
        return showingService.getShowingMovies(movieName);
    }

    @PostMapping("/book/{showingId}")
    public ResponseEntity<String> bookSeats(@PathVariable int showingId, @RequestBody List<String> seats) {
        try {
            showingService.saveBooking(showingId, seats);
            return ResponseEntity.ok("Seats booked successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/addshowing")
    public ResponseEntity<Showing> addShowing(@RequestBody Showing showing) throws JsonProcessingException {

        showing.setMovie(objectMapper.readValue(movieApi.getByTitle("Suzume"), Movie.class));

        Showing saveShowing = showingService.addShowing(showing);
        return ResponseEntity.ok(saveShowing);

    }
}



