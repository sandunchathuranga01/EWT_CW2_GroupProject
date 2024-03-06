package com.EWT.DisposalPlace.repo;

import com.EWT.DisposalPlace.entity.DisposalPlace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisposalRepo extends JpaRepository<DisposalPlace,Integer> {
}
