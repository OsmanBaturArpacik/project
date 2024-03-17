package com.javagps.core.model;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Date;

@Data
@Service
public class SignUpModel {
    private int userId;
    private String phoneNumber;
    private String email;
    private String gender;
    private Date birthdate;
}
