package com.example.CinemaTicketServer.Controller;


import com.example.CinemaTicketServer.Model.Movie;
import com.example.CinemaTicketServer.Model.Showing;
import com.example.CinemaTicketServer.MovieApi;
import com.example.CinemaTicketServer.Repository.MovieRepository;
import com.example.CinemaTicketServer.Service.MovieService;
import com.example.CinemaTicketServer.Service.ShowingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/showing")
public class ShowingController {

    @Autowired
    private ShowingService showingService;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieApi movieApi;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MovieService movieService;

    @GetMapping("/showings")
    public List<Showing> getShowings() {
        return showingService.getShowings();
    }

//    This is likely unneeded
//    @GetMapping("/getAtDay")
//    @ResponseBody
//    public ArrayList<Showing> getShowingsByDay(@PathVariable OffsetDateTime date){
//        return showingService.getAllAfterTime(date);
//    }

    @GetMapping("/movie")
    public List<Showing> getShowingMovies(@RequestParam int id) {
        return showingService.getShowingMovies(id);
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

    @PostMapping("/reallyAddShowing")

    public ResponseEntity<String> reallyAddShowing(@RequestParam int screenNumber, @RequestParam String dateOfStart, @RequestParam String dateOfEnd, @RequestParam int movieId, @RequestParam String bookingType){

        showingService.addShowings(screenNumber, dateOfStart, dateOfEnd, movieId, bookingType);

        return null;
    }


    @PostMapping("/addShowing")
    public ResponseEntity<Showing> addShowing(@RequestBody Showing showing) throws JsonProcessingException {
        if (showing.getMovie() == null || showing.getMovie().getTitle() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        String title = showing.getMovie().getTitle();
        Movie movie = movieRepository.findByTitle(title);

        if (movie == null) {
            movie = objectMapper.readValue(movieApi.getByTitle(title), Movie.class);
            if (movie == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
            movieRepository.save(movie);
        }

        showing.setMovie(movie);
        Showing savedShowing = showingService.addShowing(showing);

        return ResponseEntity.ok(savedShowing);
    }

    @DeleteMapping("/deleteById")
    void deleteById(@RequestParam int id){
        showingService.deleteById(id);
    }


}



