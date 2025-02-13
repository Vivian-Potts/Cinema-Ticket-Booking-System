package com.example.CinemaTicketServer.Controller;


import com.example.CinemaTicketServer.Model.Showing;
import com.example.CinemaTicketServer.Service.ShowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShowingController {

    @Autowired
    private ShowingService showingService;

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
}



