package com.example.CinemaTicketServer.Model;

import jakarta.persistence.Id;

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

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getAgeRating() {
        return ageRating;
    }

    public void setAgeRating(String ageRating) {
        this.ageRating = ageRating;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
