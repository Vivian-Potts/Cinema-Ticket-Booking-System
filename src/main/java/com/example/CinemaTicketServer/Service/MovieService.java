package com.example.CinemaTicketServer.Service;

import com.example.CinemaTicketServer.Model.Movie;
import com.example.CinemaTicketServer.MovieApi;
import com.example.CinemaTicketServer.Repository.MovieRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepo;
    @Autowired
    private MovieApi movieApi;
    @Autowired
    private ObjectMapper objectMapper;




    public void saveMovie(Movie movie){
        movieRepo.save(movie);
    }

    public Optional<Movie> getMovie(String name) throws JsonProcessingException {
        Movie movie = movieRepo.findByTitle("Blue Lock: Episode Nagi");
        if (movie == null){
            movie = objectMapper.readValue(movieApi.getByTitle(name),Movie.class);
        }
        //THIS IS NOT GONNA HANDLE NULL VERY WELL PLEASE DONT JUST LEAVE IT PLEASE
        return Optional.ofNullable(movie);

    }


}
