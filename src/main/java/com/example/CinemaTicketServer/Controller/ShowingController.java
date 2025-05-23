package com.example.CinemaTicketServer.Controller;


import com.example.CinemaTicketServer.Model.Showing;
import com.example.CinemaTicketServer.Service.ShowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class ShowingController {

    @Autowired
    private ShowingService showingService;

    //Get all showings
    @GetMapping("/showings")
    public List<Showing> getShowings() {
        return showingService.getShowings();
    }


    //Get Movie by ID
    @GetMapping("/getShowingMovie/{id}")
    public ArrayList<Showing> getShowingMovies(@PathVariable int id) {
        return (ArrayList<Showing>) showingService.getShowingMovies(id);
    }



}



