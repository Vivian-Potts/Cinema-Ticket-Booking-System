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

    public AdminUsers findUser(String username) {
        return auRepo.findByUsername(username);
    }

    public AdminUsers addUser(String username, String password) {
        String hashedPass = BCrypt.hashpw(password, BCrypt.gensalt(10));
        AdminUsers user = new AdminUsers();
        user.setUsername(username);
        user.setPassword(hashedPass);
        auRepo.save(user);

        return user;
    }


    public boolean validateUser(String username, String password) {
        AdminUsers user = auRepo.findByUsername(username);
        if (user == null) {
            return false;
        }
        return BCrypt.checkpw(password, user.getPassword());
    }


}




