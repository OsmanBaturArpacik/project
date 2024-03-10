package com.javagps.core.model;

import org.springframework.stereotype.Component;

@Component
public class Queries {
    public static final String LOGIN_QUERY = "SELECT * FROM tbl_users WHERE username=? AND password=?";
}
