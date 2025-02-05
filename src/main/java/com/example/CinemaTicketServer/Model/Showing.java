package com.example.CinemaTicketServer.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
@Entity
@Getter
@Setter
public class Showing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int showingId;
    private Movie movie;
    private int screenNumber;
    private OffsetDateTime timeOfStart;
    private OffsetDateTime timeOfFinish;
    private String bookedSeats;

    Showing(){
        for(int i = 0; i < 200; i++){
            bookedSeats.concat("E");
        }

    }
}
