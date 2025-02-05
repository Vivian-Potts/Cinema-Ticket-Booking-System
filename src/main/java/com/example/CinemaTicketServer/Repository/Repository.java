package com.example.CinemaTicketServer.Repository;

import com.example.CinemaTicketServer.Model.TestClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.*;


@org.springframework.stereotype.Repository
public interface Repository extends JpaRepository<TestClass, Integer> {

}
