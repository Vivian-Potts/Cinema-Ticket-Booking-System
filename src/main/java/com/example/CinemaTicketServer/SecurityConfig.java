package com.example.CinemaTicketServer;

import com.example.CinemaTicketServer.Repository.AdminUsersRepository;
import com.example.CinemaTicketServer.Service.AdminUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


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
                        .requestMatchers(new AntPathRequestMatcher("/book/**"),
                                new AntPathRequestMatcher("/movie/**"),
                                new AntPathRequestMatcher("/showings"),
                                new AntPathRequestMatcher("/getAtDay"),
                                new AntPathRequestMatcher("/get"),
                                new AntPathRequestMatcher("/id/**"),
                                new AntPathRequestMatcher("/error"))
                        .permitAll()
                        .requestMatchers("/users/*", "/addshowing").hasRole("ADMIN")
                        .anyRequest().authenticated()
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
