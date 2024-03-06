package com.EWT.DisposalPlace.service;

import com.EWT.DisposalPlace.Msg.VarList;
import com.EWT.DisposalPlace.dto.DisposalDTO;
import com.EWT.DisposalPlace.entity.DisposalPlace;
import com.EWT.DisposalPlace.repo.DisposalRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DisposalService {

    @Autowired
    private DisposalRepo disposalRepo;
    @Autowired
    private ModelMapper modelMapper;


    public String saveDisposalPlace(DisposalDTO disposalDTO){
        if (disposalRepo.existsById(disposalDTO.getLocationID())){
            return VarList.RSP_DUPLICATED;
        }else {
            disposalRepo.save(modelMapper.map(disposalDTO, DisposalPlace.class));
            return VarList.RSP_SUCCESS;
        }
    }
/*
    public String updateDisposalPlace(DisposalDTO disposalDTO){
        if (disposalRepo.existsById(disposalDTO.getLocationID())){
            disposalRepo.save(modelMapper.map(disposalDTO, DisposalPlace.class));
            return VarList.RSP_SUCCESS;

        }else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }*/

    public List<DisposalDTO> getAllDisposalPlaces(){
        List<DisposalPlace>disposalPlacesList=disposalRepo.findAll();
        return modelMapper.map(disposalPlacesList,new TypeToken<List<DisposalDTO>>(){}.getType());
    }

    public String deleteDisposalPlace(int locationID){
        if (disposalRepo.existsById(locationID)){
            disposalRepo.deleteById(locationID);
            return VarList.RSP_SUCCESS;
        }else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }
}
