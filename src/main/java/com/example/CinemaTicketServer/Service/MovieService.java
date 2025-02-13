package com.example.CinemaTicketServer.Service;

import com.example.CinemaTicketServer.Model.Movie;
import com.example.CinemaTicketServer.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepo;


    void saveMovie(Movie movie){
//        movieRepo.save(movie);
    }

    Optional<Movie> getMovie(int query){
        return movieRepo.findById(query);

    }


}
