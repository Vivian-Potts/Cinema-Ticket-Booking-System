package com.example.CinemaTicketServer.Repository;

import com.example.CinemaTicketServer.Model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {

    public ArrayList<Seat> getByShowing(int id);
}
