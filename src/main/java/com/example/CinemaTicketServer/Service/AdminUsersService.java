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

    public void findUser(String username){
        auRepo.findUser(username);
    }

    public void addUser(String username, String password){

        String hashedPass = BCrypt.hashpw(password, BCrypt.gensalt(2));

        AdminUsers user = new AdminUsers();
        user.setUsername(username);
        user.setPassword(hashedPass);

        auRepo.save(user);
    }
    public boolean validateUser(String username, String password){

        AdminUsers user = auRepo.findUser(username);
        return BCrypt.checkpw(password, user.getPassword());
    }




}
