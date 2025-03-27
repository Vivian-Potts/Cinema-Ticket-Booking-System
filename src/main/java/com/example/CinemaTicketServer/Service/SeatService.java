package com.example.CinemaTicketServer.Service;

import com.example.CinemaTicketServer.Model.Seat;
import com.example.CinemaTicketServer.Repository.SeatRepository;
import com.example.CinemaTicketServer.Repository.ShowingRepository;

public class SeatService {

    SeatRepository seatRepository;
    ShowingRepository showingRepository;

    public int bookSeat(int seatNumber, int movieId){

        if (movieId > 300 || movieId < 1) {
            try {
                Seat seat = new Seat(seatNumber, showingRepository.findById(movieId));
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
