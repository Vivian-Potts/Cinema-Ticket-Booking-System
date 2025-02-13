package com.example.CinemaTicketServer.Controller;

import com.example.CinemaTicketServer.Model.AdminUsers;
import com.example.CinemaTicketServer.Service.AdminUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminUserController {

    @Autowired
    private AdminUsersService auService;

    //Get all Users
    @GetMapping
    public List<AdminUsers> getUsers(){
       return auService.getUsers();
    }

    //Find specific user
    @GetMapping("/users/{username}")
    public AdminUsers findUser(@PathVariable String username){
        return auService.findUser(username);
    }

    //Add a user
    @PostMapping("/adduser")
    public ResponseEntity<String> addUser(@RequestParam String username, @RequestParam String password) {

        if(username == null || password == null || username.isEmpty() || password.isEmpty()){
            return ResponseEntity.badRequest().body("Username or password can't be empty");
        }
        auService.addUser(username,password);
        return ResponseEntity.ok("User added");

    }



}
