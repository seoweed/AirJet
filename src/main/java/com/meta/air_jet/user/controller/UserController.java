package com.meta.air_jet.user.controller;

import com.meta.air_jet._core.utils.JwtUtil;
import com.meta.air_jet.user.domain.dto.UserRequestDTO;
import com.meta.air_jet.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public String authenticate(@RequestBody UserRequestDTO.loginDTO dto) throws Exception {
        System.out.println("login controller 메소드 실행");
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.loginId(), dto.password())
            );
        } catch (Exception e) {
            throw new Exception("Invalid username or password", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(dto.loginId());
        String jwtToken = jwtUtil.generateToken(userDetails.getUsername());
        System.out.println("jwtToken = " + jwtToken);
        return jwtToken;
    }

}
