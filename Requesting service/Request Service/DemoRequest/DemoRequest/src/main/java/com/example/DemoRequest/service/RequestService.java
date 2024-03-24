package com.example.DemoRequest.service;

import com.example.DemoRequest.dto.RequestDTO;
import com.example.DemoRequest.entity.Request;
import com.example.DemoRequest.repo.RequestRepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class RequestService {
    @Autowired
    private RequestRepo requestRepo;
    @Autowired
    private ModelMapper modelMapper;
    public RequestDTO saveRequest(RequestDTO requestDTO){
        requestRepo.save(modelMapper.map(requestDTO, Request.class));
        return requestDTO;

    }
    public List<RequestDTO> getALLRequest(){
       List<Request>requestList =requestRepo.findAll();
       return modelMapper.map(requestList,new TypeToken<List<RequestDTO>>(){}.getType());
    }
    public RequestDTO updateRequest(RequestDTO requestDTO){
        requestRepo.save( modelMapper.map(requestDTO,Request.class));
        return requestDTO;
    }
    public boolean deleteRequest(RequestDTO requestDTO){
        requestRepo.delete(modelMapper.map(requestDTO,Request.class));
        return true;
    }

    public RequestDTO getRequestBYRequestID(String requestID){
        Request request=requestRepo.getRequestByRequestID(requestID);
        return modelMapper.map(request,RequestDTO.class);
    }

    public RequestDTO getRequestByRequestIDAndCnumber(String requestID, Long Cnumber){
        Request request = requestRepo.getRequestByIDAndCnumber(requestID, Cnumber);
        return modelMapper.map(request, RequestDTO.class);
    }


    }





    //user id > user details
    //select * from request where id =2

    // user id, address > user details
    //select * from request where id=33 and cnumber = "0"

