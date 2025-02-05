package com.example.CinemaTicketServer.Model;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Movies {

    @Id
    private int movieId;
    private  String movieName;
    private String ageRating;
    private int duration;

    public Movies(int movieId, String movieName, String ageRating, int duration){
        this.movieId = movieId;
        this.movieName = movieName;
        this.ageRating = ageRating;
        this.duration = duration;
    }

}
