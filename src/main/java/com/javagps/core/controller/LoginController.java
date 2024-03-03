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
    // localhost:8080/login?username=admin&password=admin
    @GetMapping("/login")
    public ResponseEntity<String> getUser(@RequestParam String username, @RequestParam String password) {
        loginService.setLogin(username, password);
        if (loginService.isValid(getUserPass)) {
            return ResponseEntity.status(OK).body("Login Successful");
        }
        return ResponseEntity.status(UNAUTHORIZED).body("Login Failed");
    }

//    @PostMapping(value = "/{path:^(?!login$).*$}")
//    public ResponseEntity<String> handleRequest(@PathVariable String path) {
//        return ResponseEntity.status(NOT_FOUND).body("404 - Sayfa bulunamadı");
//    }
}


//
//@RestController
//public class UserController {
//
//    private final UserService userService;
//
//    @Autowired
//    public UserController(UserService userService){
//        this.userService = userService;
//    }
//
//    @GetMapping("/user")
//    @ResponseStatus(OK)
//    public User getUser(@RequestParam Integer id){
//        Optional<User> user = userService.getUser(id);
//        return (User) user.orElse(null);
//    }
//}