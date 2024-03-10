package com.javagps.core.model;

import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class SignModel {
    private String username;
    private String password;
    public String getUsername() {
        // hashlenip dondurulecek
        int x = 0;
        return username;
    }

    public String getPassword() {
        // hashlenip dondurulecek
        int x = 0;
        return password;
    }
}
