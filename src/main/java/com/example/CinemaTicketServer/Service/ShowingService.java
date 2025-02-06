package com.example.CinemaTicketServer.Service;

import com.example.CinemaTicketServer.Model.Showing;
import com.example.CinemaTicketServer.Repository.ShowingRepository;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    private ShowingRepository showingRepo;


    void saveBooking(Showing showing){
        showingRepo.save(showing);
    }
}
