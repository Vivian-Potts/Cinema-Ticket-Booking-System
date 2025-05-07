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

    public ArrayList<Movie> getMovie(String name){
        return (ArrayList<Movie>) movieRepo.findByTitleIgnoreCase(name);
    }

    public ArrayList<Movie> fetchMovie(String name) throws JsonProcessingException {

        ArrayList<Movie> apiResult = new ArrayList<>();
        Movie movie = objectMapper.readValue(movieApi.getByTitle(name), Movie.class);

        if (movie != null && movie.getTitle() != null){
            apiResult.add(movie);
            saveMovie(movie);

            System.out.println("Movie pulled from API " + movie.getTitle());
            }
        else{
            System.out.println("Movie not found");

        }
//            System.out.println("Pulled from api: "+apiResult.getFirst().getTitle());
//            if (apiResult.getFirst().getTitle() != null) {
//                saveMovie(apiResult.getFirst());
//                return apiResult;
//            }
        //THIS IS NOT GONNA HANDLE NULL VERY WELL PLEASE DONT JUST LEAVE IT PLEASE
        return apiResult;


    }
    public List<Movie> getMovieInDatabase(String name){
        return movieRepo.findByTitleIgnoreCase(name);
    }

    public ArrayList<String> getMoviePosterArt(String title) throws JsonProcessingException {

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
