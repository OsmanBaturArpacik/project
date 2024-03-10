package com.javagps.core.controller;

import com.javagps.core.model.SignModel;
import com.javagps.core.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.*;

@RestController
public class SignUpController {
    private final SignUpService signUpService;
    private final SignModel signModel;

    @Autowired
    public SignUpController(SignUpService signUpService, SignModel signModel) {
        this.signUpService = signUpService;
        this.signModel = signModel;
    }

    @PostMapping ("/signUp")
    @ResponseBody
    public ResponseEntity<String> createAccount(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("phoneNumber") String phoneNumber, @RequestParam("email") String email, @RequestParam("gender") String gender, @RequestParam("birthdate") String birthdate) {
        if(signUpService.mymethod()) {
            // log_account_created
            return ResponseEntity.status(OK).body("Account Created Successfully");
        }
        return ResponseEntity.status(UNAUTHORIZED).body("Create Account Failed");
    }

}
