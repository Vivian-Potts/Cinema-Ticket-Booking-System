package com.example.CinemaTicketServer;

import com.example.CinemaTicketServer.Repository.AdminUsersRepository;
import com.example.CinemaTicketServer.Service.AdminUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Autowired
    private AdminUsersRepository auRepo;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // BCrypt encoder for password matching
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/book/{showingId}", "/movie/{id}", "/showings", "/getAtDay").permitAll()  // Public endpoints
                        .requestMatchers("/users/{username}", "/adduser", "/addshowing").hasRole("ADMIN")  // Restricted to admin
                        .anyRequest().authenticated()  // Require authentication for other requests
                )
                .formLogin(withDefaults())  // Default form login
                .logout(withDefaults());  // Default logout configuration

        return http.build();
    }

    @Bean
    public AdminUserDetailService adminUserDetailService(PasswordEncoder passwordEncoder) {
        return new AdminUserDetailService(auRepo, passwordEncoder);  // Pass passwordEncoder to AdminUserDetailService
    }
}

