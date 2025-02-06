package com.example.CinemaTicketServer.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  String movieName;
    private String ageRating;
    private int duration;

    public Movie(String movieName, String ageRating, int duration){
        this.movieName = movieName;
        this.ageRating = ageRating;
        this.duration = duration;
    }
}
