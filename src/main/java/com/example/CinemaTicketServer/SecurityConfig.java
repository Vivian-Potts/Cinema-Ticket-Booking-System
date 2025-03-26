package com.example.CinemaTicketServer;

import com.example.CinemaTicketServer.Repository.AdminUsersRepository;
import com.example.CinemaTicketServer.Service.AdminUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
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
                                .requestMatchers("/admin/**").hasRole("ADMIN") //Include all admin only

                                .requestMatchers("/").permitAll()
                                .requestMatchers("/showings").permitAll()
                                .requestMatchers("/get", "/getPoster").permitAll()
                                .requestMatchers("/movie/**").permitAll()
                                .requestMatchers("/book/**").permitAll() //Doesn't Work

                                .anyRequest().authenticated()


//
                )
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                .logout(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable); // Disable CSRF

        return http.build();

    }


    @Bean
    public AdminUserDetailService adminUserDetailService() {
        return new AdminUserDetailService(auRepo, passwordEncoder());
    }
}
