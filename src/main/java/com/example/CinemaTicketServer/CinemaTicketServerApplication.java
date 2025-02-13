package com.example.CinemaTicketServer;

import com.example.CinemaTicketServer.Model.Movie;
import com.example.CinemaTicketServer.Service.MovieService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.sql.SQLException;

@SpringBootApplication
public class CinemaTicketServerApplication {

	@Autowired
    private MovieService movieService;

	public static void main(String[] args) throws SQLException, IOException {
		ApplicationContext context = SpringApplication.run(CinemaTicketServerApplication.class, args);

		MovieApi movieApi = (MovieApi) context.getBean(MovieApi.class);

		ObjectMapper objectMapper = (ObjectMapper)context.getBean(ObjectMapper.class);

		String a = movieApi.getByTitle("The Godfather");

		System.out.println(a);

		Movie movie = objectMapper.readValue(a,Movie.class);

		System.out.println(movie.getTitle());










	}
}
