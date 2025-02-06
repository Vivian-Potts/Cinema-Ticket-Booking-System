package com.example.CinemaTicketServer.Service;

import com.example.CinemaTicketServer.Model.Movie;
import com.example.CinemaTicketServer.Model.Showing;
import com.example.CinemaTicketServer.Repository.MovieRepository;
import com.example.CinemaTicketServer.Repository.ShowingRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    private ShowingRepository showingRepo;
    @Autowired
    private MovieRepository movieRepo;

    void saveMovie(Movie movie){
        movieRepo.save(movie);
    }

    Optional<Movie> getMovie(int query){
        return movieRepo.findById(query);
        
    }


    void saveBooking(Showing showing){
        showingRepo.save(showing);
    }
}
