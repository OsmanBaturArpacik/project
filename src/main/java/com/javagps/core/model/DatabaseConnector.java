package com.javagps.core.model;

import org.springframework.stereotype.Component;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.DriverManager;

@Component
public class DatabaseConnector {

    @Autowired
    public static Connection connectDb() {
        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/mynotes","root","");
            return connect;
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
