package com.javagps.core.service;

import com.javagps.core.model.SignModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@Service
public class SignUpService {
    private final SignModel signModel;
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Autowired
    public SignUpService(SignModel signModel) {
        this.signModel = signModel;
    }

    public boolean mymethod() {
        // check for username and email from db
        return false;
    }
}
