package com.example.CinemaTicketServer.Service;

import com.example.CinemaTicketServer.Model.Seat;
import com.example.CinemaTicketServer.Repository.SeatRepository;
import com.example.CinemaTicketServer.Repository.ShowingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SeatService {

    @Autowired
    SeatRepository seatRepository;
    @Autowired
    ShowingRepository showingRepository;

    public int bookSeat(int seatNumber, int id){

        if (seatNumber >= 1 && seatNumber <= 300){
            try {
                Seat seat = new Seat(seatNumber, showingRepository.findById(id));
                seatRepository.save(seat);
                return 0;
            } catch (Exception e) {
                return 1;
            }
        }else {
            return 2;
        }
    }

    public ArrayList<Seat> getByShowingId(int id){
        return seatRepository.getByShowingId(id);
    }

}
