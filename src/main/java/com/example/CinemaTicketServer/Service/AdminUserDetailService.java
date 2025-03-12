package com.example.CinemaTicketServer.Service;

import com.example.CinemaTicketServer.Model.AdminUsers;
import com.example.CinemaTicketServer.Repository.AdminUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;


@Primary
@Service
public class AdminUserDetailService implements UserDetailsService {

    @Autowired
    private AdminUsersRepository auRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;  // Spring Security's password encoder

    public AdminUserDetailService(AdminUsersRepository auRepo, PasswordEncoder passwordEncoder) {

        this.auRepo = auRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch the admin user from the database
        AdminUsers adminUser = auRepo.findByUsername(username);

        if (adminUser == null) {
            throw new UsernameNotFoundException("User not found");
        }

        // Return Spring Security's User object with username, password, and roles
        return new User(adminUser.getUsername(), adminUser.getPassword(), getAuth(Collections.singleton("ROLE_ADMIN")));
    }

    // Convert roles into Spring Security GrantedAuthority objects
    private Collection<? extends GrantedAuthority> getAuth(Set<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));  // Each role is turned into a GrantedAuthority
        }

        return authorities;
    }
}








