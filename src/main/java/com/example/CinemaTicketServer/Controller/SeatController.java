package com.example.CinemaTicketServer.Controller;


import com.example.CinemaTicketServer.Model.Seat;
import com.example.CinemaTicketServer.Model.Showing;
import com.example.CinemaTicketServer.Service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SeatController {

    @Autowired
    SeatService seatService;

    @PostMapping("/book/{id}")
    public ResponseEntity<String> bookSeat(@PathVariable int id, @RequestParam int seatNumber){

        return switch (seatService.bookSeat(seatNumber, id)) {
            case (0) -> ResponseEntity.ok("Seat Booked");
            case (1) -> ResponseEntity.badRequest().body("Showing id could not be found in database");
            case (2) -> ResponseEntity.badRequest().body("Seats range from 1 to 300");
            default -> ResponseEntity.badRequest().body("Unknown error");
        };

    }

    @GetMapping("/getSeats/{id}")
    public List<Seat> getSeatsByShowingId(@PathVariable int id){
        return seatService.getByShowingId(id);
    }

}
