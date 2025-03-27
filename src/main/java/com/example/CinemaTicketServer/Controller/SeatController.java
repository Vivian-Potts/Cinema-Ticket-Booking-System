package com.example.CinemaTicketServer.Controller;


import com.example.CinemaTicketServer.Service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SeatController {

    @Autowired
    SeatService seatService;

    @PostMapping("/book")
    public ResponseEntity<String> bookSeat(@PathVariable int seatNumber, @PathVariable int showingId){

        return switch (seatService.bookSeat(seatNumber, showingId)) {
            case (0) -> ResponseEntity.ok("Seat Booked");
            case (1) -> ResponseEntity.badRequest().body("Movie id could not be found in database");
            case (2) -> ResponseEntity.badRequest().body("Seats range from 1 to 300");
            default -> ResponseEntity.badRequest().body("Unknown error");
        };

    }

}
