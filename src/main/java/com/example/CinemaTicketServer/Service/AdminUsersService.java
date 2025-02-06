package com.example.CinemaTicketServer.Service;

import com.example.CinemaTicketServer.Model.AdminUsers;
import com.example.CinemaTicketServer.Repository.AdminUsersRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminUsersService {

    @Autowired
    private AdminUsersRepository auRepo;

    static String hashPass(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(5));
    }

    static boolean checkPass(String password, String hashedPass){
        return BCrypt.checkpw(password, hashedPass);
    }
}
