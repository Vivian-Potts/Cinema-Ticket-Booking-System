package com.example.CinemaTicketServer;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class MovieApi {
    public String getByTitle(String query){

        if (query.equals("Blue Lock: Episode Nagi")){
            return "{\"Title\":\"Blue Lock: Episode Nagi\",\"Year\":\"2024\",\"Rated\":\"PG-13\",\"Released\":\"28 Jun 2024\",\"Runtime\":\"91 min\",\"Genre\":\"Animation, Drama, Sport\",\"Director\":\"Shunsuke Ishikawa\",\"Writer\":\"Muneyuki Kaneshiro, Taku Kishimoto, Yusuke Nomura\",\"Actors\":\"Nobunaga Shimazaki, Yuma Uchida, Kazuyuki Okitsu\",\"Plot\":\"Seishiro Nagi before he enters the titular Blue Lock facility.\",\"Language\":\"Japanese\",\"Country\":\"Japan\",\"Awards\":\"N/A\",\"Poster\":\"https://m.media-amazon.com/images/M/MV5BNjliYmU5N2EtYmNjNi00NGM0LWEzZWItN2Q0YzM5YTk4Y2Q1XkEyXkFqcGc@._V1_SX300.jpg\",\"Ratings\":[{\"Source\":\"Internet Movie Database\",\"Value\":\"6.6/10\"}],\"Metascore\":\"N/A\",\"imdbRating\":\"6.6\",\"imdbVotes\":\"2,270\",\"imdbID\":\"tt28671146\",\"Type\":\"movie\",\"DVD\":\"N/A\",\"BoxOffice\":\"$1,855,384\",\"Production\":\"N/A\",\"Website\":\"N/A\",\"Response\":\"True\"}\n";
        }
//        else{
//            if (true == true) {
//                return null;
//            }
//        }

        //Code created by Chat-gpt
        try {
            //Adapt query to URL
            query.equals(query.replaceAll(" ","+"));

            String dummyUrl = "http://www.omdbapi.com/?t=" + query + "&plot=full&apikey=bab8d5a7";

            // Create a URL object with the API endpoint
            URL url = new URL(dummyUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            // Set the request method to GET
            con.setRequestMethod("GET");

            // Connect to the API
            con.connect();

            // Check the response code to make sure the connection was successful
            if (con.getResponseCode() == 200) {
                // Create a BufferedReader to read the response from the input stream
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                // Read the response line by line
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                // Close the BufferedReader
                in.close();

                // Print the JSON response
                con.disconnect();
                return response.toString();
            } else {
                con.disconnect();
                return "Error: " + con.getResponseMessage();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
