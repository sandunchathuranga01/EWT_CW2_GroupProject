package com.EWT.DisposalPlace.RequestingServices.Requests;

import com.EWT.DisposalPlace.RequestingServices.repository.RequestRepository;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requests")
public class RequestController {
    @Autowired
    private RequestRepository requestRepository;

    @GetMapping
    public List<Request> getAllRequests(){
        return requestRepository.findAll();
    }

    @PostMapping
    public Request createRequest(@RequestBody Request request){
        return requestRepository.save(request);
    }
}

