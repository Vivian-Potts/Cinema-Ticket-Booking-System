package com.example.CinemaTicketServer.Repository;

import com.example.CinemaTicketServer.Model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    Movie findByTitle(String title);
    List<Movie> findByTitleIgnoreCase(String title);


}