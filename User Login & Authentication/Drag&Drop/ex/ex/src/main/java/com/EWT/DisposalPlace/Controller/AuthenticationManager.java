package com.EWT.DisposalPlace.Controller;
import org.springframework.security.core.Authentication;
public interface AuthenticationManager extends JwtTokenProvider  {
    Authentication authenticate(Authentication authentication);
}
