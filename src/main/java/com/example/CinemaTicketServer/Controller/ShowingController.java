package com.example.CinemaTicketServer.Controller;


import com.example.CinemaTicketServer.Model.Showing;
import com.example.CinemaTicketServer.Service.ShowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
public class ShowingController {

    @Autowired
    private ShowingService showingService;

    //Get all showings
    @GetMapping("/showings")
    public List<Showing> getShowings() {
        return showingService.getShowings();
    }

//    This is likely unneeded
//    @GetMapping("/getAtDay")
//    @ResponseBody
//    public ArrayList<Showing> getShowingsByDay(@PathVariable OffsetDateTime date){
//        return showingService.getAllAfterTime(date);
//    }



    //Get Movie by ID
    @GetMapping("/movie/{id}")
    public List<Showing> getShowingMovies(@PathVariable int id) {
        return showingService.getShowingMovies(id);
    }


//    //Book showing by ID
//    @PostMapping("/book")
//    public ResponseEntity<String> bookSeats(@PathVariable int id) {
//        try {
//            showingService.saveBooking(id);
//            return ResponseEntity.ok("Seats booked successfully");
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }


}



