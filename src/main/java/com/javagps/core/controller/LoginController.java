package com.javagps.core.controller;

import com.javagps.core.model.Login;
import com.javagps.core.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import static com.javagps.core.model.Queries.getUserPass;
import static org.springframework.http.HttpStatus.*;


@RestController
public class LoginController {
    private final LoginService loginService;
    private final Login login;

    @Autowired
    public LoginController(LoginService loginService, Login login) {
        this.loginService = loginService;
        this.login = login;
    }
    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<String> getUser(@RequestParam("username") String username, @RequestParam("password") String password) {
        loginService.setLogin(username, password);
        if (loginService.isValid(getUserPass)) {
            return ResponseEntity.status(OK).body("Login Successful");
        }
        return ResponseEntity.status(UNAUTHORIZED).body("Login Failed");
    }

}


//        Optional<User> user = userService.getUser(id);
//        return (User) user.orElse(null);

