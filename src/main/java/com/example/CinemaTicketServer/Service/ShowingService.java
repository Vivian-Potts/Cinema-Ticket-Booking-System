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


//    // Method for booking seats
//    public void saveBooking(int id) {
//
//        Showing showing = showingRepo.findById(id);
//        if (showing == null) {
//            throw new IllegalArgumentException("Showing not found");
//        }
//        validateBooking(showing);
//
//        List<String> bookedSeats = getBookedSeats(showing);
//        //updateSeats(showing, bookedSeats);
//    }



//    // Ensure booking is valid
//    private void validateBooking(Showing showing) {
//        if (showing == null) {
//            throw new IllegalArgumentException("Showing cannot be null");
//        }
//        if (seats == null || seats.isEmpty()) {
//            throw new IllegalArgumentException("Seats cannot be null or empty");
//        }
//    }

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

    public Showing addShowing(Showing showing) {

        if (showing.getMovie() == null || showing.getMovie().getTitle() == null) {
            throw new RuntimeException("Movie or Movie title is null");
        }

        String title = showing.getMovie().getTitle();
        String year = showing.getMovie().getYear();

        List<Movie> movies = movieRepo.findByTitleIgnoreCase(title);
        if (movies == null || movies.isEmpty()) {
            throw new RuntimeException("Movie not found");
        }

        Movie movie = movies.get(0);

        showing.setMovie(movie);
        Showing savedShowing = showingRepo.save(showing);

        return savedShowing;
    }

    public void addShowings(int screenNumber, String dateOfStart, String dateOfEnd, int movieId, String bookingType){

        ArrayList<Showing> showings = new ArrayList<Showing>();

        Movie movie = movieRepo.getReferenceById(movieId);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ssXXX");

        OffsetDateTime startDate = OffsetDateTime.parse(dateOfStart+"+00:00", dateTimeFormatter);
        OffsetDateTime endDate = OffsetDateTime.parse(dateOfEnd+"+00:00", dateTimeFormatter);

        //All booking have 5 mins added to them to account for seconds being absent and for maintenance
        Long movieLength =  Long.parseLong(movie.getRuntime().replace(" min",""))+5;



        if (bookingType.equalsIgnoreCase("once")){

            showings.add(new Showing(screenNumber, startDate, startDate.plusMinutes(movieLength) ,movie));

        } else if (bookingType.equalsIgnoreCase("daily")) {
            OffsetDateTime cursor = startDate;
            Showing dummyShowing = new Showing(screenNumber, startDate, endDate, movie);

            while(cursor.isBefore(endDate)){
                System.out.println("Added new object");
                showings.add(new Showing(screenNumber, cursor, cursor.plusMinutes(movieLength), movie));
                System.out.println(dummyShowing.getTimeOfStart());
                cursor = cursor.plusDays(1);
            }
        }

        System.out.println(showings.size());

//        for(int i = 0; i < showings.size(); i++){
//            showingRepo.save(showings.get(i));
//        }

        for (Showing qwerty : showings){
            System.out.println("Attempted showing save"+qwerty.getTimeOfStart());

            showingRepo.save(qwerty);
        }

        //showingRepo.saveAll(showings);



    }


    public ArrayList<Showing> getAllAfterTime(OffsetDateTime time){
        return (ArrayList<Showing>) showingRepo.findByTimeOfStartGreaterThanEqual(time);
        //return null;
    }

    public Showing deleteById(int id){
        return showingRepo.deleteById(id);
    }



//    // Update seats after booking
//    private void updateSeats(Showing showing, List<String> bookedSeats) {
//        String updatedSeats = String.join(",", bookedSeats);
//        showing.setSeats(updatedSeats);
//        showingRepo.save(showing);
//    }

}
