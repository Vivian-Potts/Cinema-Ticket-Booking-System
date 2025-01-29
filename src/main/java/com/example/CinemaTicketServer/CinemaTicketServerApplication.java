package com.example.CinemaTicketServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class CinemaTicketServerApplication {

	public static void main(String[] args) throws SQLException {
		Repository repo = new Repository();
		SpringApplication.run(CinemaTicketServerApplication.class, args);
	}

}
