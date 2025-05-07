package com.example.CinemaTicketServer.Repository;

import com.example.CinemaTicketServer.Model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Integer> {
    public List<Seat> findByShowingId(int showing_id);

    ArrayList<Seat> getByShowingId(int id);
}
