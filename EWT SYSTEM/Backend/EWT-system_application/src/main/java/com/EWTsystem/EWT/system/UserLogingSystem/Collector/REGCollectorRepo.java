package com.EWTsystem.EWT.system.UserLogingSystem.Collector;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface REGCollectorRepo extends JpaRepository<REGCollector, Integer> {

    List<REGCollector> findAllByEmail(String email);

    List<REGCollector> findAll();

}
