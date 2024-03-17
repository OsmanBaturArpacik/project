package com.javagps.core.service;

import com.javagps.core.model.DatabaseConnector;
import com.javagps.core.model.SignInModel;
import com.javagps.core.model.SignUpModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.text.SimpleDateFormat;

@Service
public class SignUpService {
    private final SignUpModel signUpModel;
    private final SignInModel signInModel;
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private final static int DEFAULT_ROLE_ID = 1;
    public static final String CHECK_USERNAME = "SELECT username FROM tbl_users WHERE username=?";
    public static final String CHECK_EMAIL = "SELECT email FROM tbl_user_details WHERE email=?";
    public static final String GET_USER_ID = "SELECT id FROM tbl_user WHERE username=?";
    public static final String CREATE_USER = "INSERT INTO tbl_users (username, password, role_id) VALUES(?,?,?)";
    public static final String CREATE_USER_DETAILS = "INSERT INTO tbl_user_details (user_id, phone_num, email, gender, birthdate) VALUES(?,?,?,?,?)";

    @Autowired
    public SignUpService(SignUpModel signUpModel, SignInModel signInModel) {
        this.signUpModel = signUpModel;
        this.signInModel = signInModel;
    }
    public void setSignInModel(String username, String password) {
        signInModel.setUsername(username);
        signInModel.setPassword(password);
    }
    public void setSignUpModel(String userId, String phoneNumber, String email, String gender, String birthdate) {
        signUpModel.setUserId(Integer.parseInt(userId));
        signUpModel.setPhoneNumber(phoneNumber);
        signUpModel.setEmail(email);
        signUpModel.setGender(gender);
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            signUpModel.setBirthdate(dateFormat.parse(birthdate));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    // if username exist return true else return false
    public boolean checkUsername() {
        try {
            connection = DatabaseConnector.connectDb();
            preparedStatement = connection.prepareStatement(CHECK_USERNAME);
            preparedStatement.setString(1, signInModel.getUsername());
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                // that's mean user exist cause resultset have next
                return true;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        // user is not exist
        return false;
    }
    public boolean checkEmail() {
        try {
            connection = DatabaseConnector.connectDb();
            preparedStatement = connection.prepareStatement(CHECK_EMAIL);
            preparedStatement.setString(1, signUpModel.getEmail());
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                // that's mean user exist cause resultset have next
                return true;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        // user is not exist
        return false;
    }
    // boolean yerine dierkt response entity döndürsün
    public boolean createAccount() {
        // tbl_users created
        try {
            connection = DatabaseConnector.connectDb();
            preparedStatement = connection.prepareStatement(CREATE_USER);
            preparedStatement.setString(1, signInModel.getUsername());
            preparedStatement.setString(2, signInModel.getPassword());
            preparedStatement.setInt(3, DEFAULT_ROLE_ID);
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected > 0) {
                System.out.println("Successfully added");
            } else {
                System.out.println("Error");
            }
            statement = connection.createStatement();
            resultSet = statement.executeQuery(GET_USER_ID);
            if(resultSet.next()) {
                signUpModel.setUserId(resultSet.getInt("id"));
            }
            preparedStatement = connection.prepareStatement(CREATE_USER_DETAILS);
            preparedStatement.setInt(1, signUpModel.getUserId());
            preparedStatement.setString(2, signUpModel.getPhoneNumber());
            preparedStatement.setString(3, signUpModel.getEmail());
            preparedStatement.setString(4, signUpModel.getGender());
            preparedStatement.setDate(5, (Date) signUpModel.getBirthdate());
            int rowsAffecteds = preparedStatement.executeUpdate();
            if(rowsAffecteds > 0) {
                System.out.println("Successfully added");
            } else {
                System.out.println("Error");
            }
            return true;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        // get user_id
        // set userid signupmodel

        // sign up write on db

        return false;
    }
}
