package com.example.CinemaTicketServer.Service;

import com.example.CinemaTicketServer.Model.Movie;
import com.example.CinemaTicketServer.MovieApi;
import com.example.CinemaTicketServer.Repository.MovieRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


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

    public ArrayList<Movie> getMovie(String name) throws JsonProcessingException {

        ArrayList<Movie> results = (ArrayList<Movie>) movieRepo.findByTitleIgnoreCase(name);

        if (results.isEmpty()){
            ArrayList<Movie> apiResult = new ArrayList<>();
            apiResult.add(objectMapper.readValue(movieApi.getByTitle(name), Movie.class));
            System.out.println("Pulled from api: "+apiResult.getFirst().getTitle());
            if (apiResult.getFirst().getTitle() != null) {
                saveMovie(apiResult.getFirst());
                return apiResult;
            }
        }
        //THIS IS NOT GONNA HANDLE NULL VERY WELL PLEASE DONT JUST LEAVE IT PLEASE
        return results;


    }
    public List<Movie> getMovieInDatabase(String name){
        return movieRepo.findByTitleIgnoreCase(name);
    }

    public ArrayList<String> getMoviePosterArt(String title) throws JsonProcessingException {

        System.out.println("Getting movie: "+title);

        ArrayList<Movie> movies = getMovie(title);

        ArrayList<String> posterArts = new ArrayList<String>();

        if(movies.isEmpty()){
            return posterArts;
        }

        for (Movie movie: movies){
            posterArts.add(movie.getPoster());
        }

        return posterArts;
    }


}
