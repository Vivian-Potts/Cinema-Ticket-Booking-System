package com.example.CinemaTicketServer;

import com.example.CinemaTicketServer.Model.Movie;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.sql.SQLException;

@SpringBootApplication
public class CinemaTicketServerApplication {

	public static void main(String[] args) throws SQLException {
		ApplicationContext context = SpringApplication.run(CinemaTicketServerApplication.class, args);

		Movie movie = new Movie("Movie","18",90);

	}
}
