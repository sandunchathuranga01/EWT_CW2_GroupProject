package com.EWT.DisposalPlace.Controller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.Authentication;
public interface JwtTokenProvider extends JpaRepository {
    String generateToken(Authentication authentication);
}
