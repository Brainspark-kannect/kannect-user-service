package com.kannect.user.service.auth.service;

import com.kannect.user.service.dto.request.LoginRequestDTO;
import com.kannect.user.service.dto.response.LoginResponseDTO;
import com.kannect.user.service.exception.LoginFailedException;

public interface AuthService {

	LoginResponseDTO handleLogin(LoginRequestDTO request) throws LoginFailedException;

}
