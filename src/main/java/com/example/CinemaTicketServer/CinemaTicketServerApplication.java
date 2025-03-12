package com.example.CinemaTicketServer;

import com.example.CinemaTicketServer.Model.Movie;
import com.example.CinemaTicketServer.Service.MovieService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;
import java.sql.SQLException;

@SpringBootApplication
public class CinemaTicketServerApplication {
	public static void main(String[] args) throws SQLException, IOException {
		ApplicationContext context = SpringApplication.run(CinemaTicketServerApplication.class, args);

//		BCryptPasswordEncoder pass = new BCryptPasswordEncoder();
//		String p = "password";
//		String h = pass.encode(p);
//		System.out.println("Hash pass: " + h);
	}
}
