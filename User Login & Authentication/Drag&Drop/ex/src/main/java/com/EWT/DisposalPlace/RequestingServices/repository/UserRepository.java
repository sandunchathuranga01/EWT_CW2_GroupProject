package com.EWT.DisposalPlace.RequestingServices.repository;
import com.EWT.DisposalPlace.RequestingServices.UserA;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserA, Long> {
    UserA findByName(String username);
}
