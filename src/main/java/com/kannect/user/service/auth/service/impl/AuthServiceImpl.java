package com.kannect.user.service.auth.service.impl;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kannect.user.service.auth.entity.User;
import com.kannect.user.service.auth.repository.UserRepository;
import com.kannect.user.service.auth.service.AuthService;
import com.kannect.user.service.dto.request.LoginRequestDTO;
import com.kannect.user.service.dto.response.LoginResponseDTO;
import com.kannect.user.service.exception.LoginFailedException;
import com.kannect.user.service.security.CustomUserDetailsService;
import com.kannect.user.service.utils.DecryptionUtil;
import com.kannect.user.service.utils.JwtUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final CustomUserDetailsService userDetailsService;
	private final JwtUtil jwtUtil;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final DecryptionUtil decryptionUtil;

	@Override
	public LoginResponseDTO handleLogin(LoginRequestDTO request) throws LoginFailedException {
		String decryptedPassword = decryptionUtil.decrypt(request.getPassword());

		User user = userRepository.findByUserNameAndActive(request.getUserName(),true)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
		
		if (!user.getActive()) {
			throw new LoginFailedException("User account is not active. Please contact administrator or hr.");
		}

		if (!passwordEncoder.matches(decryptedPassword, user.getPassword())) {
			throw new LoginFailedException("Invalid credentials");
		}

		// Update last login
		user.setLastLogin(LocalDateTime.now());
		userRepository.save(user);

		UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserName());
		String token = jwtUtil.generateToken(userDetails);

		Set<String> roles = user.getRoles().stream().map(role -> {
			return role.getRoleName();

		}).collect(Collectors.toSet());

		return new LoginResponseDTO(token, user.getUserName(), roles);
	}

}
