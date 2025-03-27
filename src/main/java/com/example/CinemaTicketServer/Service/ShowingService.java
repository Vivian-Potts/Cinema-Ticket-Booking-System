package com.example.CinemaTicketServer.Service;

import com.example.CinemaTicketServer.Model.Movie;
import com.example.CinemaTicketServer.Model.Showing;
import com.example.CinemaTicketServer.Repository.MovieRepository;
import com.example.CinemaTicketServer.Repository.ShowingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class ShowingService {
    @Autowired
    private ShowingRepository showingRepo;
    @Autowired
    private MovieRepository movieRepo;
    @Autowired
    MovieService movieService;

    // Get all showings
    public List<Showing> getShowings() {
        return showingRepo.findAll();
    }

    // Get showings for a specific movie
    public List<Showing> getShowingMovies(int id) {
        return showingRepo.findByMovieId(id);
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

    //Add a showing

    public Boolean addShowings(int screenNumber, String dateOfStart, String dateOfEnd, int movieId, String bookingType) {

        ArrayList<Showing> showings = new ArrayList<Showing>();
        Movie movie = movieRepo.getReferenceById(movieId);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ssXXX");
        OffsetDateTime startDate = OffsetDateTime.parse(dateOfStart + "+00:00", dateTimeFormatter);
        OffsetDateTime endDate = OffsetDateTime.parse(dateOfEnd + "+00:00", dateTimeFormatter);

        //All booking have 5 mins added to them to account for seconds being absent and for maintenance
        Long movieLength = Long.parseLong(movie.getRuntime().replace(" min", "")) + 5;

//        int days = -1;


        if (bookingType.equalsIgnoreCase("Once")) {

            showings.add(new Showing(screenNumber, startDate, startDate.plusMinutes(movieLength), movie));

        } else {

            OffsetDateTime cursor = startDate;
            Showing dummyShowing = new Showing(screenNumber, startDate, endDate, movie);

            if (bookingType.equalsIgnoreCase("Monthly")) {
                for (int i = 0; i < 12; i++) {
                    showings.add(new Showing(screenNumber, startDate.plusMonths(i), cursor.plusMonths(i).plusMinutes(movieLength), movie));
                }
            }

            int days = -1;

            if (bookingType.equalsIgnoreCase("Daily")) {
                days = 1;
            } else if (bookingType.equalsIgnoreCase("Weekly")) {
                days = 7;
            }

            if (days != -1) {
                while (cursor.isBefore(endDate)) {
                    System.out.println("Added new object");
                    showings.add(new Showing(screenNumber, cursor, cursor.plusMinutes(movieLength), movie));
                    System.out.println(dummyShowing.getTimeOfStart());
                    cursor = cursor.plusDays(days);
                }
            }
        }

        if (showings.isEmpty()) {
            return Boolean.FALSE;
        }

        for (Showing showing : showings) {
            showingRepo.save(showing);
        }

//        showingRepo.saveAll(showings);


        return Boolean.TRUE;
    }


    public ArrayList<Showing> getAllAfterTime(OffsetDateTime time) {
        return (ArrayList<Showing>) showingRepo.findByTimeOfStartGreaterThanEqual(time);
        //return null;
    }

    public Showing deleteById(int id) {
        return showingRepo.deleteById(id);
    }

}

