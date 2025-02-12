package com.example.CinemaTicketServer;

import com.example.CinemaTicketServer.Model.AdminUsers;
import com.example.CinemaTicketServer.Repository.AdminUsersRepository;
import com.example.CinemaTicketServer.Service.AdminUsersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AdminUsersServiceTest {

    @Autowired
    private AdminUsersService adminUsersService;

    @Autowired
    private AdminUsersRepository adminUsersRepository;

    @Test
    @Transactional
    public void testAddUserPasswordHashing() {

        String username = "testuser";
        String password = "testpass";

        adminUsersService.addUser(username, password);

        AdminUsers userFromDb = adminUsersRepository.findByUsername(username);

        assertThat(userFromDb).isNotNull();
        assertThat(userFromDb.getPassword()).isNotEqualTo(password);
        assertThat(userFromDb.getPassword()).startsWith("$2a$");

        System.out.println("Username: " + userFromDb.getUsername());
        System.out.println("Hashed Password: " + userFromDb.getPassword());
    }

    @Test
    @Transactional
    public void testValidateUserPassword() {

        String username = "testuser";
        String password = "testpass";

        adminUsersService.addUser(username, password);

        boolean isValid = adminUsersService.validateUser(username, password);
        assertThat(isValid).isTrue();

        boolean isInvalid = adminUsersService.validateUser(username, "wrongPassword");

        assertThat(isInvalid).isFalse();
    }
}
