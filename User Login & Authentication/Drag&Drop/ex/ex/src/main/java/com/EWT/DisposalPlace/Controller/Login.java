package com.EWT.DisposalPlace.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class Login {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserRepository userRespository;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateuser(@RequestBody LoginRequest loginRequest){

        Authentication authentication = authenticationManager.authenticate(
                (Authentication) new LoginRequest(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
               )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtTokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtTokenProvider[]{
        });
    }
}
