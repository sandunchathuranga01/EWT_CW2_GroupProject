package com.EWTsystem.EWT.system.DisposalPlace;

import com.EWTsystem.EWT.system.Other.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DisposalPlaceService {

    @Autowired
    private DisposalPlaceRepo disposalPlaceRepo;
    @Autowired
    private ModelMapper modelMapper;


    public String saveDisposalPlace(DisposalPlaceDTO disposalPlaceDTO){
        if (disposalPlaceRepo.existsById(disposalPlaceDTO.getLocationID())){
            return VarList.RSP_DUPLICATED;
        }else {
            disposalPlaceRepo.save(modelMapper.map(disposalPlaceDTO, DisposalPlace.class));
            return VarList.RSP_SUCCESS;
        }
    }

    public String updateDisposalPlace(DisposalPlaceDTO disposalPlaceDTO){
        if (disposalPlaceRepo.existsById(disposalPlaceDTO.getLocationID())){
            disposalPlaceRepo.save(modelMapper.map(disposalPlaceDTO, DisposalPlace.class));
            return VarList.RSP_SUCCESS;
        }else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    public List<DisposalPlaceDTO> getAllDisposalPlaces(){
        List<DisposalPlace>disposalPlacesList=disposalPlaceRepo.findAll();
        return modelMapper.map(disposalPlacesList,new TypeToken<List<DisposalPlaceDTO>>(){}.getType());
    }

    public String deleteDisposalPlace(int locationID){
        if (disposalPlaceRepo.existsById(locationID)){
            disposalPlaceRepo.deleteById(locationID);
            return VarList.RSP_SUCCESS;
        }else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }
}
