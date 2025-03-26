package com.example.CinemaTicketServer.Controller;

import com.example.CinemaTicketServer.Model.AdminUsers;
import com.example.CinemaTicketServer.Model.Movie;
import com.example.CinemaTicketServer.Model.Showing;
import com.example.CinemaTicketServer.MovieApi;
import com.example.CinemaTicketServer.Repository.MovieRepository;
import com.example.CinemaTicketServer.Repository.ShowingRepository;
import com.example.CinemaTicketServer.Service.AdminUsersService;
import com.example.CinemaTicketServer.Service.MovieService;
import com.example.CinemaTicketServer.Service.ShowingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminUserController {

    @Autowired
    private AdminUsersService auService;
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
    @Autowired
    private ShowingRepository showingRepository;


    // Get all users
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<AdminUsers> getUsers() {
        return auService.getUsers();
    }

    // Find a specific user by username
    @GetMapping("/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public AdminUsers findUser(@PathVariable String username) {
        return auService.findUser(username);
    }

    // Find a specific user by id
    @GetMapping("/id/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public AdminUsers findUserId(@PathVariable int id) {
        return auService.findUserId(id);
    }

    // Add a new user (admin only)
    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> addUser(@RequestParam String username, @RequestParam String password) {
        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
            return ResponseEntity.badRequest().body("Username or password can't be empty");
        }
        auService.addUser(username, password);
        return ResponseEntity.ok("User added");
    }

    //Add Showing
    @PostMapping("/reallyAddShowing")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> reallyAddShowing(@RequestParam int screenNumber, @RequestParam String dateOfStart, @RequestParam String dateOfEnd, @RequestParam int movieId, @RequestParam String bookingType){

        showingService.addShowings(screenNumber, dateOfStart, dateOfEnd, movieId, bookingType);

        return null;
    }

    //May be deleted
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

    //Delete showing
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Showing deleteById(@PathVariable int id){
       return showingService.deleteById(id);
    }

}
