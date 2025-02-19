package com.example.CinemaTicketServer;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class MovieApi {
    public String getByTitle(String query){

        System.out.println("Querying API");
        //Code created by Chat-gpt
        try {
            //Adapt query to URL
            query = (query.replaceAll(" ","+"));

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
