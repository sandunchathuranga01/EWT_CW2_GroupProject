package com.EWTsystem.EWT.system.SmartBin;

import com.EWTsystem.EWT.system.Other.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SmartBinService {

    @Autowired
    private SmartBinRepo smartBinRepo;
    @Autowired
    private ModelMapper modelMapper;

    public String saveSmartbin(SmartBinDTO smartBinDTO) {
        if (smartBinRepo.existsById(smartBinDTO.getBinId())) {
            return VarList.RSP_DUPLICATED;
        } else {
            smartBinRepo.save(modelMapper.map(smartBinDTO, SmartBin.class));
            return VarList.RSP_SUCCESS;
        }
    }

    public String updateSmartbin(SmartBinDTO smartBinDTO) {
        if (smartBinRepo.existsById(smartBinDTO.getBinId())) {
            smartBinRepo.save(modelMapper.map(smartBinDTO, SmartBin.class));
            return VarList.RSP_SUCCESS;
        } else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }
    public List<SmartBinDTO> getAllSmartbin() {
        List<SmartBin> smartbinLIst = smartBinRepo.findAll();
        return modelMapper.map(smartbinLIst, new TypeToken<List<SmartBinDTO>>() {
        }.getType());
    }
    public List<SmartBinDTO> getfilledSmartBin() {
        List<SmartBin> FilledSmartbinLIst = smartBinRepo.filledSmartBins();
        return modelMapper.map(FilledSmartbinLIst, new TypeToken<List<SmartBinDTO>>() {
        }.getType());

    }
    public List<SmartBinDTO> getNOTfilledSmartBin() {
        List<SmartBin> NOTFilledSmartbinLIst = smartBinRepo.getNotFilledSmartBins();
        return modelMapper.map(NOTFilledSmartbinLIst, new TypeToken<List<SmartBinDTO>>() {
        }.getType());

    }
    public String deleteSmartbin(int BinID) {
        if (smartBinRepo.existsById(BinID)) {
            smartBinRepo.deleteById(BinID);
            return VarList.RSP_SUCCESS;
        } else {
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    @Transactional
    public void updateDistance(int binId, Long distance) {
        Optional<SmartBin> optionalSmartBin = smartBinRepo.findById(binId);
        if (optionalSmartBin.isPresent()) {
            SmartBin smartBin = optionalSmartBin.get();
            smartBin.setDistance(distance);
            smartBinRepo.save(smartBin);
        } else {
            // Handle the case where the smart bin with the given ID is not found
        }
    }
}


