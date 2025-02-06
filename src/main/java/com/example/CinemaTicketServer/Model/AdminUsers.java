package com.example.CinemaTicketServer.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;


@Component
@Getter
@Setter
@Entity


public class AdminUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String username;
    private String password;

    public AdminUsers(String username, String password){
        this.username = username;
        this.password = password;
    }












}
