package com.example.DemoRequest.repo;

import com.example.DemoRequest.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RequestRepo extends JpaRepository<Request,Integer> {

    @Query(value = "SELECT * FROM Request WHERE ID= ?1", nativeQuery = true)
    Request getRequestByRequestID(String requestID);

    @Query(value = "SELECT * FROM Request WHERE ID = ?1 AND Cnumber = ?2", nativeQuery = true)
    Request getRequestByIDAndCnumber(String requestID, long Cnumber);

}



