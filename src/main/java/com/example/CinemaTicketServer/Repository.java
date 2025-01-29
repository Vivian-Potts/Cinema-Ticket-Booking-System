package com.example.CinemaTicketServer;

import java.sql.*;

public class Repository {
    Repository() throws SQLException {
        String url = "jdbc:sqlite:C:\\Users\\vivian.potts\\Documents\\Databases\\CinemaDatabaseStuff\\Master.db";

        Connection con = DriverManager.getConnection(url);

        //PreparedStatement a = con.prepareStatement("INSERT INTO Movies VALUES(100,'John','19',12)");
        //a.execute();
        PreparedStatement b = con.prepareStatement("Select * FROM Movies");
        System.out.println(b.executeQuery().next());
        con.close();
    }
}
