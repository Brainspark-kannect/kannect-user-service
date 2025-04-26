package com.kannect.user.service.auth.service;

import com.kannect.user.service.dto.request.LoginRequestDTO;
import com.kannect.user.service.dto.response.LoginResponseDTO;

public interface AuthService {

	LoginResponseDTO handleLogin(LoginRequestDTO request);

}
