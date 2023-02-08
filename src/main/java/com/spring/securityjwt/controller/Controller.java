package com.spring.securityjwt.controller;

import com.spring.securityjwt.models.AuthenticationRequest;
import com.spring.securityjwt.models.AuthenticationResponse;
import com.spring.securityjwt.services.MyUserDetailService;
import com.spring.securityjwt.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.PasswordAuthentication;

@RestController

public class Controller {


    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenUtil util;

    @Autowired
    MyUserDetailService myUserDetailService;

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }


    @RequestMapping(value = "/authenticate" ,method = RequestMethod.POST)
   public ResponseEntity<Object> authenticate(@RequestBody AuthenticationRequest auth){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(auth.getUserName(),auth.getPassword()));

        UserDetails detail = myUserDetailService.loadUserByUsername(auth.getUserName());

        String jwt =  util.generateToken(detail);
        return ResponseEntity.status(HttpStatus.OK).body(new AuthenticationResponse(jwt));

    }
}
