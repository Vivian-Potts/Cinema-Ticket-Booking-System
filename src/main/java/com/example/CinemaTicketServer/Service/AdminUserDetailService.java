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


@Service
@Primary
public class AdminUserDetailService implements UserDetailsService {

    private final AdminUsersRepository auRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminUserDetailService(AdminUsersRepository auRepo, PasswordEncoder passwordEncoder) {
        this.auRepo = auRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AdminUsers adminUser = auRepo.findByUsername(username);
        if (adminUser == null) {
            throw new UsernameNotFoundException("User not found");
        }

        // Return Spring Security's User object with username, password, and roles
        return new User(adminUser.getUsername(), adminUser.getPassword(), getAuthorities());
    }

    private Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }
}








