package com.example.CinemaTicketServer.Service;

import com.example.CinemaTicketServer.Model.Showing;
import com.example.CinemaTicketServer.Repository.ShowingRepository;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    private ShowingRepository repo;


    void saveBooking(Showing showing){
        repo.save(showing);
    }
}
