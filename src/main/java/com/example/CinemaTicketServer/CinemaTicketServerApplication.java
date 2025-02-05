package com.example.CinemaTicketServer;

import com.example.CinemaTicketServer.Model.TestClass;
import com.example.CinemaTicketServer.Repository.Repository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.sql.SQLException;

@SpringBootApplication
public class CinemaTicketServerApplication {

	public static void main(String[] args) throws SQLException {
		ApplicationContext context = SpringApplication.run(CinemaTicketServerApplication.class, args);
		TestClass data = context.getBean(TestClass.class);
		data.numbers = 55;
		data.testColumn = "Hello World!";
		Repository repo = context.getBean(Repository.class);
		repo.save(data);
	}
}
