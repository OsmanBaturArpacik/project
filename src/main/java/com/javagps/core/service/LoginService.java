package com.javagps.core.service;

import com.javagps.core.model.DatabaseConnector;
import com.javagps.core.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;

@Service
public class LoginService {
    private Login login;
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    @Autowired
    public LoginService(Login login) {
        this.login = login;
    }

    public void setLogin(String username, String password) {
        login.setUsername(username);
        login.setPassword(password);
    }

//    public LoginService(String username, String password) {
//        login = new Login(username, password);
//    }

    public boolean isValid(String query) {
        try {
            connection = DatabaseConnector.connectDb();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, login.getUsername());
            preparedStatement.setString(2, login.getPassword());
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

//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class UserService {
//
//    private List<User> userList;
//
//    public UserService() {
//        userList = new ArrayList<>();
//
//        User user1 = new User(1,"Ida", 32, "ida@mail.com");
//        User user2 = new User(2,"Hans", 26, "hans@mail.com");
//        User user3 = new User(3,"Lars", 45, "lars@mail.com");
//        User user4 = new User(4,"Ben", 32, "ben@mail.com");
//        User user5 = new User(5,"Eva", 59, "eva@mail.com");
//
//        userList.addAll(Arrays.asList(user1,user2,user3,user4,user5));
//    }
//
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

