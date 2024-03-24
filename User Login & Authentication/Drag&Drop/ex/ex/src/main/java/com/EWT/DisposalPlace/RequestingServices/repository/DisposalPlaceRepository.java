package com.EWT.DisposalPlace.RequestingServices.repository;
import java.util.List;

import com.EWT.DisposalPlace.RequestingServices.DisposalPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisposalPlaceRepository extends JpaRepository<DisposalPlace,Long> {
    List<DisposalPlace> findByNameContainingIgnoreCase(String name);
}
