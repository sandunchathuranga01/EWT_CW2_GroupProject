package com.EWTsystem.EWT.system.RequestingService;

import com.EWTsystem.EWT.system.Other.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RequestingService {

    @Autowired
    private RequestRepo requestRepo;
    @Autowired
    private ModelMapper modelMapper;

    public String saveRequestingService(RequestDTO requestDTO){
        if (requestRepo.existsById(requestDTO.getRequestid())){
            return VarList.RSP_DUPLICATED;
        }else {
            requestRepo.save(modelMapper.map(requestDTO, Request.class));
            return VarList.RSP_SUCCESS;
        }
    }

    public String updateRequestingService(RequestDTO requestDTO){
        if (requestRepo.existsById(requestDTO.getRequestid())){
            requestRepo.save(modelMapper.map(requestDTO, Request.class));
            return VarList.RSP_SUCCESS;
        }else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    public List<RequestDTO> getAllRequestingService(){
        List<Request> disposalPlacesList=requestRepo.findAll();
        return modelMapper.map(disposalPlacesList,new TypeToken<List<RequestDTO>>(){}.getType());
    }

    public String deleteRequestingService(int requestid){
        if (requestRepo.existsById(requestid)){
            requestRepo.deleteById(requestid);
            return VarList.RSP_SUCCESS;
        }else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }
}

