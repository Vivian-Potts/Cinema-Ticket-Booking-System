package com.example.CinemaTicketServer.Repository;

import java.sql.*;

public class Repository {
    public Repository() throws SQLException {
        String url = "jdbc:sqlite:C:\\Users\\efi.whiteley\\Documents\\Databases\\Master.db";

        Connection con = DriverManager.getConnection(url);

        //PreparedStatement a = con.prepareStatement("INSERT INTO Movies VALUES(100,'John','19',12)");
        //a.execute();
        PreparedStatement b = con.prepareStatement("Select * FROM Movies");
        System.out.println(b.executeQuery().next());
        con.close();
    }
}
