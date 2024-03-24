package com.EWTsystem.EWT.system.UserLogingSystem.Client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface UserRpo extends JpaRepository<Users, Integer> {

    List<Users> findAllByEmail(String email);

    List<Users> findAll();

}
