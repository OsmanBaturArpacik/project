package com.javagps.core.service;

import com.javagps.core.model.DatabaseConnector;
import com.javagps.core.model.SignInModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

@Service
public class SignInService {
    private final SignInModel signInModel;
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    public static final String CHECK_USERNAME_PASSWORD = "SELECT * FROM tbl_users WHERE username=? AND password=?";


    @Autowired
    public SignInService(SignInModel signInModel) {
        this.signInModel = signInModel;
    }

    public void setSignInModel(String username, String password) {
        signInModel.setUsername(username);
        signInModel.setPassword(password);
    }

//    public LoginService(String username, String password) {
//        login = new Login(username, password);
//    }

    public boolean isValid() {
        try {
            connection = DatabaseConnector.connectDb();
            preparedStatement = connection.prepareStatement(CHECK_USERNAME_PASSWORD);
            preparedStatement.setString(1, signInModel.getUsername());
            preparedStatement.setString(2, signInModel.getPassword());
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                return true;
            }
        }
        catch (Exception ex) {
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

