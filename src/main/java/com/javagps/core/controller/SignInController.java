package com.javagps.core.controller;

import com.javagps.core.model.SignModel;
import com.javagps.core.service.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static com.javagps.core.model.Queries.LOGIN_QUERY;
import static org.springframework.http.HttpStatus.*;


@RestController
public class SignInController {
    private final SignInService signInService;
    private final SignModel signModel;

    @Autowired
    public SignInController(SignInService signInService, SignModel signModel) {
        this.signInService = signInService;
        this.signModel = signModel;
    }
    @PostMapping("/signIn")
    @ResponseBody
    public ResponseEntity<String> loginAuthentication(@RequestParam("username") String username, @RequestParam("password") String password) {
        signInService.setLogin(username, password);
        if (signInService.isValid(LOGIN_QUERY)) {
            // create jwt
            // log_login
            return ResponseEntity.status(OK).body("Login Successful");
        }
        return ResponseEntity.status(UNAUTHORIZED).body("Login Failed");
    }

}


//        Optional<User> user = userService.getUser(id);
//        return (User) user.orElse(null);

