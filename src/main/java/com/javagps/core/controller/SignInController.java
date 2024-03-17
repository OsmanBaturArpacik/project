package com.javagps.core.controller;

import com.javagps.core.model.SignInModel;
import com.javagps.core.service.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;


@RestController
public class SignInController {
    private final SignInService signInService;
    private final SignInModel signInModel;

    @Autowired
    public SignInController(SignInService signInService, SignInModel signInModel) {
        this.signInService = signInService;
        this.signInModel = signInModel;
    }
    @PostMapping("/signIn")
    @ResponseBody
    public ResponseEntity<String> loginAuthentication(@RequestParam("username") String username, @RequestParam("password") String password) {
        signInService.setSignInModel(username, password);
        if (signInService.isValid()) {
            // create jwt
            // log_login
            return ResponseEntity.status(OK).body("Login Successful");
        }
        return ResponseEntity.status(UNAUTHORIZED).body("Login Failed");
    }

}


//        Optional<User> user = userService.getUser(id);
//        return (User) user.orElse(null);

