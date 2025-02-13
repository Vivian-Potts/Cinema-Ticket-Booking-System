package com.example.CinemaTicketServer.Model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@Entity


public class Movie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int moveId;
    @Column(name = "movie_name")
    private String movieName;
    private String ageRating;
    private int duration;

}
