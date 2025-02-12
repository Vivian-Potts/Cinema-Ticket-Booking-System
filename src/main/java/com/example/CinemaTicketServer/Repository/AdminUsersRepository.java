package com.example.CinemaTicketServer.Repository;

import com.example.CinemaTicketServer.Model.AdminUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminUsersRepository extends JpaRepository<AdminUsers, Integer> {

AdminUsers findByUsername(String username);
}
