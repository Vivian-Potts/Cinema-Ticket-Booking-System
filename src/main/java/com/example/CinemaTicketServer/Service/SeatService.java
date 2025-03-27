package com.example.CinemaTicketServer.Service;

import com.example.CinemaTicketServer.Model.Seat;
import com.example.CinemaTicketServer.Repository.SeatRepository;
import com.example.CinemaTicketServer.Repository.ShowingRepository;
import org.springframework.stereotype.Service;

@Service
public class SeatService {

    SeatRepository seatRepository;
    ShowingRepository showingRepository;

    public int bookSeat(int seatNumber, int id){

        if (seatNumber > 300 || seatNumber < 1) {
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

}
