package com.example.CinemaTicketServer.Service;
import com.example.CinemaTicketServer.Model.AdminUsers;
import com.example.CinemaTicketServer.Repository.AdminUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminUsersService {

    @Autowired
    private AdminUsersRepository auRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AdminUsers findUser(String username) {
        return auRepo.findByUsername(username);
    }

    public List<AdminUsers> getUsers() {
        return auRepo.findAll();
    }

    public AdminUsers addUser(String username, String password) {
        String hashedPass = passwordEncoder.encode(password); // encode passwords
        AdminUsers user = new AdminUsers();
        user.setUsername(username);
        user.setPassword(hashedPass);
        AdminUsers saveUser = auRepo.save(user);
        System.out.println("Saved User");
        return saveUser;
    }

    public boolean validateUser(String username, String password) {
        AdminUsers user = auRepo.findByUsername(username);
        return user != null && passwordEncoder.matches(password, user.getPassword());
    }

    public AdminUsers findUserId(int id) {
        return auRepo.findById(id);
    }
}




