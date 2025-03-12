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
        return new BCryptPasswordEncoder(); // Use BCrypt for password encoding
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/book/{showingId}", "/movie/{id}", "/showings", "/getAtDay", "/get").permitAll() // Public endpoints
                        .requestMatchers("/users/{username}", "/addshowing", "/add").hasRole("ADMIN") // Restricted to ADMIN
                        .anyRequest().authenticated() // All other requests require authentication
                )
                .formLogin(withDefaults()) // Enable form login
                .logout(withDefaults()); // Enable logout

        return http.build();
    }

    @Bean
    public AdminUserDetailService adminUserDetailService() {
        return new AdminUserDetailService(auRepo, passwordEncoder());
    }
}
