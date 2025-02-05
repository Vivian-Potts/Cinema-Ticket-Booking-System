package com.example.CinemaTicketServer.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.OffsetDateTime;

@Component
@Entity
@Getter
@Setter
public class Showings {
    @Id
    private int showingId;
    private int movieId;
    private int screenNumber;
    private OffsetDateTime timeOfStart;
    private OffsetDateTime timeOfFinish;
    private String bookedSeats;

    Showings(){
        for(int i = 0; i < 200; i++){
            bookedSeats.concat("E");
        }

    }
}
