package com.example.CinemaTicketServer.Controller;

import com.example.CinemaTicketServer.MovieApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private MovieApi movieApi;

    @GetMapping("/get")
    @ResponseBody
    public String showExampleMovie(){
        System.out.println(movieApi.getByTitle("Blue Lock: Episode Nagi"));
        return movieApi.getByTitle("Blue Lock: Episode Nagi");
    }

}
