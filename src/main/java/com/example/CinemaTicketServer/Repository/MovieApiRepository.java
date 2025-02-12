package com.example.CinemaTicketServer.Repository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MovieApiRepository {
    void getByTitle(){
        //Code created by Chat-gpt
        try {
            // Create a URL object with the API endpoint
            URL url = new URL("http://www.omdbapi.com/?t=The+Lego+Movie&plot=full&apikey=bab8d5a7");
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
                    System.out.println(inputLine);
                    response.append(inputLine);
                }
                // Close the BufferedReader
                in.close();

                // Print the JSON response
                System.out.println(response.toString());
            } else {
                System.out.println("Error: " + con.getResponseMessage());
            }

            // Disconnect the connection
            con.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
