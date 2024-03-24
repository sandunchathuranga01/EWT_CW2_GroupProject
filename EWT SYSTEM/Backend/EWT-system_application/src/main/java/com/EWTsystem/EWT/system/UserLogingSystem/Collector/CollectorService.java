package com.EWTsystem.EWT.system.UserLogingSystem.Collector;

import com.EWTsystem.EWT.system.Other.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class    CollectorService {
    @Autowired
    private REGCollectorRepo regCollectorRepo;
    @Autowired
    private CollectorRepo collectorRepo;
    @Autowired
    private ModelMapper modelMapper;
    public String saveCollector(CollectorDTO collectorDTO){
        if (collectorRepo.existsById(collectorDTO.getId())){
            return VarList.RSP_DUPLICATED;
        }else {
            collectorRepo.save(modelMapper.map(collectorDTO , Collector.class));
            return VarList.RSP_SUCCESS;
        }
    }

    public String saveAsREGCollector(REGCollectorDTO regCollectorDTO){
        if (collectorRepo.existsById(regCollectorDTO.getId())){
            return VarList.RSP_DUPLICATED;
        }else {
            regCollectorRepo.save(modelMapper.map(regCollectorDTO , REGCollector.class));
            return VarList.RSP_SUCCESS;
        }
    }
    public String updateCollector(CollectorDTO collectorDTO){
        if (collectorRepo.existsById(collectorDTO.getId())){
            collectorRepo.save(modelMapper.map(collectorDTO,Collector.class));
            return VarList.RSP_SUCCESS;
        }else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    public List<CollectorDTO> getAllCollector(){
        List<Collector> userList=collectorRepo.findAll();
        return modelMapper.map(userList,new TypeToken<List<CollectorDTO>>(){}.getType());
    }

    public List<REGCollectorDTO> getAllREGCollector(){
        List<REGCollector> REGCollectorList=regCollectorRepo.findAll();
        return modelMapper.map(REGCollectorList,new TypeToken<List<REGCollectorDTO>>(){}.getType());
    }

    public String deleteCollector(int id){
        if (collectorRepo.existsById(id)){
            collectorRepo.deleteById(id);
            return VarList.RSP_SUCCESS;
        }else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }
    public String deleteREGCollector(int id){
        if (regCollectorRepo.existsById(id)){
            regCollectorRepo.deleteById(id);
            return VarList.RSP_SUCCESS;
        }else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }
}
