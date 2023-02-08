package com.spring.securityjwt.models;

import lombok.Data;

@Data
public class AuthenticationRequest {

    private String userName;

    private String password;
}
