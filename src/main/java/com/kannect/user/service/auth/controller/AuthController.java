package com.kannect.user.service.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kannect.user.service.auth.interfaces.IAuthController;
import com.kannect.user.service.auth.service.AuthService;
import com.kannect.user.service.dto.request.LoginRequestDTO;
import com.kannect.user.service.dto.response.SuccessResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@Validated
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AuthController implements IAuthController{
    
    private final AuthService authService;

    @Override
    @PostMapping("/login")
    public ResponseEntity<SuccessResponse> login(@RequestBody LoginRequestDTO request) {
		return ResponseEntity.ok(new SuccessResponse(HttpStatus.OK.value(), HttpStatus.OK, "User LoggedIn",
				authService.handleLogin(request)));
    }
}

