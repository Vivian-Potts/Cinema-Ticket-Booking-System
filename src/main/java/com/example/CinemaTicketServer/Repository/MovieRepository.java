package com.example.CinemaTicketServer.Repository;

import com.example.CinemaTicketServer.Model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;



@org.springframework.stereotype.Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
}