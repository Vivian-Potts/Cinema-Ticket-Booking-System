package com.example.CinemaTicketServer.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.stereotype.Component;

@Entity(name="test_new_again")
@Component
public class TestClass {
    @Id
    public int numbers;
    public String testColumn;
}
