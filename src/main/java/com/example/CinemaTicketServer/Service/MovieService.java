package com.example.CinemaTicketServer.Service;

import com.example.CinemaTicketServer.Model.Movie;
import com.example.CinemaTicketServer.MovieApi;
import com.example.CinemaTicketServer.Repository.MovieRepository;
import com.example.CinemaTicketServer.StringCapitaliser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.type.descriptor.java.CharacterArrayJavaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
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

    public List<Movie> getMovie(String name) throws JsonProcessingException {



        name = StringCapitaliser.capitaliseString(name);

        List<Movie> results = movieRepo.findByTitleIgnoreCase(name);
        if (results.isEmpty()){
            List<Movie>apiResult = Collections.singletonList(objectMapper.readValue(movieApi.getByTitle(name), Movie.class));
            System.out.println("Pulled from api: "+apiResult.getFirst().getTitle());
            if (apiResult.getFirst().getTitle() != null) {
                saveMovie(apiResult.getFirst());
                return apiResult;
            }
        }
        //THIS IS NOT GONNA HANDLE NULL VERY WELL PLEASE DONT JUST LEAVE IT PLEASE
        return results;


    }


}
