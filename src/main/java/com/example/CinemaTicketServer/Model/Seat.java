package com.example.CinemaTicketServer.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Seat {

    @Id
    private int seatNumber;
    @ManyToOne
    @JoinColumn(name = "showing_id")
    private Showing showing;

    public Seat(int seatNumber, Showing showing){
        this.seatNumber = seatNumber;
        this.showing = showing;
    }

    public Showing getShowing() {
        return showing;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public Seat(){}

}


