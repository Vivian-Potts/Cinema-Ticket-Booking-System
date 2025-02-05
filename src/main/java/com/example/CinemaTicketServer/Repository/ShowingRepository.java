package com.example.CinemaTicketServer.Repository;

import com.example.CinemaTicketServer.Model.Showing;
import org.springframework.data.jpa.repository.JpaRepository;



@org.springframework.stereotype.Repository
public interface ShowingRepository extends JpaRepository<Showing, Integer> {
}


