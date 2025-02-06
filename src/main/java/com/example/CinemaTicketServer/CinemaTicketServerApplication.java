package com.example.CinemaTicketServer;

import com.example.CinemaTicketServer.Model.AdminUsers;
import com.example.CinemaTicketServer.Model.Movie;
import kong.unirest.core.HttpResponse;
import kong.unirest.core.Unirest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.sql.SQLException;

@SpringBootApplication
public class CinemaTicketServerApplication {

	public static void main(String[] args) throws SQLException {
		ApplicationContext context = SpringApplication.run(CinemaTicketServerApplication.class, args);

		//Movie movie = new Movie("Movie","18",90);





//		HttpResponse<String> response = Unirest.get("https://moviedatabase8.p.rapidapi.com/Search/Incep")
//				.header("x-rapidapi-key", "dc694cf22bmsh248518358bf652ap111db4jsnd3bc78035382")
//				.header("x-rapidapi-host", "moviedatabase8.p.rapidapi.com")
//				.asString();
//
//		System.out.println(response.getHeaders());






	}
}
