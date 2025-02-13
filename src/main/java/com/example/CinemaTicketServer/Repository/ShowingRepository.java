package com.example.CinemaTicketServer.Repository;

import com.example.CinemaTicketServer.Model.Showing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ShowingRepository extends JpaRepository<Showing, Integer> {

    Showing findById(int id);

    List<Showing> findByMovieTitle(String title);
}


