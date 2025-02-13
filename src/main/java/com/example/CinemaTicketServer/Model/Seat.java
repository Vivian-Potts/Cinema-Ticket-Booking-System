package com.example.CinemaTicketServer.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seatId;
    private int seatNumber;
    @ManyToOne
    @JoinColumn(name = "showing_id")
    private Showing showing;
    private boolean isBooked;


}


