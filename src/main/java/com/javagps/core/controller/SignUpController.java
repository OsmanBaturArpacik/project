package com.javagps.core.controller;

import com.javagps.core.model.SignInModel;
import com.javagps.core.model.SignUpModel;
import com.javagps.core.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.*;

@RestController
public class SignUpController {
    private final SignUpService signUpService;
    private final SignUpModel signUpModel;

    @Autowired
    public SignUpController(SignUpService signUpService, SignUpModel signUpModel) {
        this.signUpService = signUpService;
        this.signUpModel = signUpModel;
    }


    @PostMapping ("/signUp")
    @ResponseBody
    public ResponseEntity<String> createAccount(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("phoneNumber") String phoneNumber, @RequestParam("email") String email, @RequestParam("gender") String gender, @RequestParam("birthdate") String birthdate) {
        signUpService.setSignInModel(username, password);
        if (signUpService.checkUsername()) {
            return ResponseEntity.status(UNAUTHORIZED).body("This username is taken");
        }
        signUpService.setSignUpModel(null, phoneNumber, email, gender, birthdate);
        if(signUpService.checkEmail()) {
            return ResponseEntity.status(UNAUTHORIZED).body("This email already used");
        }
        if(signUpService.createAccount()) {
            // log_account_created
            return ResponseEntity.status(OK).body("Account Created Successfully");
        }
        return ResponseEntity.status(UNAUTHORIZED).body("Create Account Failed");
    }

}
