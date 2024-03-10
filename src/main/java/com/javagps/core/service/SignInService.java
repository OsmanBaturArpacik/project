package com.javagps.core.service;

import com.javagps.core.model.DatabaseConnector;
import com.javagps.core.model.SignModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@Service
public class SignInService {
    private final SignModel signModel;
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Autowired
    public SignInService(SignModel signModel) {
        this.signModel = signModel;
    }

    public void setLogin(String username, String password) {
        signModel.setUsername(username);
        signModel.setPassword(password);
    }

//    public LoginService(String username, String password) {
//        login = new Login(username, password);
//    }

    public boolean isValid(String query) {
        try {
            connection = DatabaseConnector.connectDb();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, signModel.getUsername());
            preparedStatement.setString(2, signModel.getPassword());
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                return true;
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}


//    public Optional<User> getUser(Integer id) {
//        Optional<User> optional = Optional.empty();
//        for (User user: userList) {
//            if(id == user.getId()){
//                optional = Optional.of(user);
//                return optional;
//            }
//        }
//        return optional;
//    }
//}

