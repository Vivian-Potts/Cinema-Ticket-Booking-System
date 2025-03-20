package com.example.CinemaTicketServer.Model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
//@Getter
//@Setter
public class Showing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private int screenNumber;

    private OffsetDateTime timeOfStart;
    private OffsetDateTime timeOfFinish;

    @OneToMany(mappedBy = "showing")
    private List<Seat> seats;

    @ManyToOne(targetEntity = Movie.class)
    private Movie movie;

//    public Showing(){
//        for(int i = 0; i < 200; i++){
//            bookedSeats.concat("E");
//        }
//
//   }

    public Showing(int screenNumber, OffsetDateTime timeOfStart, OffsetDateTime timeOfFinish, Movie movie){
        this.screenNumber = screenNumber;
        this.timeOfStart = timeOfStart;
        this.timeOfFinish = timeOfFinish;
        this.movie = movie;
    }

    public Showing(){}


    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public int getScreenNumber() {
        return screenNumber;
    }

    public void setScreenNumber(int screenNumber) {
        this.screenNumber = screenNumber;
    }

    public OffsetDateTime getTimeOfStart() {
        return timeOfStart;
    }

    public void setTimeOfStart(OffsetDateTime timeOfStart) {
        this.timeOfStart = timeOfStart;
    }

    public OffsetDateTime getTimeOfFinish() {
        return timeOfFinish;
    }

    public void setTimeOfFinish(OffsetDateTime timeOfFinish) {
        this.timeOfFinish = timeOfFinish;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

}
