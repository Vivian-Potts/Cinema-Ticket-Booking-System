package com.example.CinemaTicketServer.Service;

import com.example.CinemaTicketServer.Model.Showing;
import com.example.CinemaTicketServer.Repository.ShowingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class ShowingService {
    @Autowired
    private ShowingRepository showingRepo;

    // Get all showings
    public List<Showing> getShowings() {
        return showingRepo.findAll();
    }

    // Get showings for a specific movie
    public List<Showing> getShowingMovies(String title) {
        return showingRepo.findByMovieTitle(title);
    }


    // Method for booking seats
    public void saveBooking(int id, List<String> seats) {

        Showing showing = showingRepo.findById(id);
        if (showing == null) {
            throw new IllegalArgumentException("Showing not found");
        }
        validateBooking(showing, seats);

        List<String> bookedSeats = getBookedSeats(showing);
        checkSeats(seats, bookedSeats);

        bookedSeats.addAll(seats);
        //updateSeats(showing, bookedSeats);
    }



    // Ensure booking is valid
    private void validateBooking(Showing showing, List<String> seats) {
        if (showing == null) {
            throw new IllegalArgumentException("Showing cannot be null");
        }
        if (seats == null || seats.isEmpty()) {
            throw new IllegalArgumentException("Seats cannot be null or empty");
        }
    }

    // Retrieve booked seats
    private List<String> getBookedSeats(Showing showing) {
        String bookedSeats = showing.getSeats().toString();
        if (bookedSeats == null || bookedSeats.isEmpty()) {
            return new ArrayList<>();
        }
        return new ArrayList<>(Arrays.asList(bookedSeats.split(",")));
    }

    // Ensure seats are not already booked
    private void checkSeats(List<String> chosenSeats, List<String> bookedSeats) {
        for (String seat : chosenSeats) {
            if (bookedSeats.contains(seat)) {
                throw new IllegalArgumentException("Seat " + seat + " is already taken");
            }
        }
    }

    // Update seats after booking
//    private void updateSeats(Showing showing, List<String> bookedSeats) {
//        String updatedSeats = String.join(",", bookedSeats);
//        showing.setSeats(updatedSeats);
//        showingRepo.save(showing);
//    }

}
