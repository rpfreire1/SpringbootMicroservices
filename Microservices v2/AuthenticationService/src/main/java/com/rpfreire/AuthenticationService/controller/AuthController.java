package com.rpfreire.AuthenticationService.controller;

import com.rpfreire.AuthenticationService.dto.req.LogInReqDto;
import com.rpfreire.AuthenticationService.dto.req.SignInReqDto;
import com.rpfreire.AuthenticationService.dto.res.AuthResDto;
import com.rpfreire.AuthenticationService.service.UserDetailServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserDetailServiceImpl userDetailsService;
    @PostMapping("/sign-up")
    public ResponseEntity<AuthResDto> register(@RequestBody @Valid SignInReqDto signInReqDto) {
        return new ResponseEntity<AuthResDto>(this.userDetailsService.createUser(signInReqDto), HttpStatus.CREATED);
    }
    @PostMapping("/log-in")
    public ResponseEntity<AuthResDto> login(@RequestBody @Valid LogInReqDto loginReqDto) {
        return new ResponseEntity<AuthResDto>(this.userDetailsService.loginUser(loginReqDto), HttpStatus.OK);
    }

}
