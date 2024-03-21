package com.EWTsystem.EWT.system.SmartBin;

import com.EWTsystem.EWT.system.Other.ResDTO;
import com.EWTsystem.EWT.system.Other.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/smartbinHardware")
@CrossOrigin
public class SmartBinController {
    @Autowired
    private SmartBinService smartBinService;

    @Autowired
    private ResDTO resDTO;
    @PostMapping("/saveSmartbin")
    public ResponseEntity saveSmartbin(@RequestBody SmartBinDTO smartBinDTO) {
        try {
            String res = smartBinService.saveSmartbin(smartBinDTO);
            if (res.equals("00")) {
                resDTO.setCode(VarList.RSP_SUCCESS);
                resDTO.setMessage("Success");
                resDTO.setContent(smartBinDTO);
                return new ResponseEntity(resDTO, HttpStatus.ACCEPTED);
            }
            else if (res.equals("06")) {
                resDTO.setCode(VarList.RSP_DUPLICATED);
                resDTO.setMessage("Disposal place Already Registered");
                resDTO.setContent(smartBinDTO);
                return new ResponseEntity(resDTO, HttpStatus.BAD_REQUEST);

            }
            else {
                resDTO.setCode(VarList.RSP_FAIL);
                resDTO.setMessage("Error");
                resDTO.setContent(null);
                return new ResponseEntity(resDTO, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception ex) {
            resDTO.setCode(VarList.RSP_ERROR);
            resDTO.setMessage(ex.getMessage());
            resDTO.setContent(null);
            return new ResponseEntity(resDTO, HttpStatus.ACCEPTED);
        }
    }


    @PutMapping(value = "/updateSmartbin")
    public ResponseEntity updateSmartbin(@RequestBody SmartBinDTO smartBinDTO){
        try {
            String res=smartBinService.updateSmartbin(smartBinDTO);
            if (res.equals("00")){
                resDTO.setCode(VarList.RSP_SUCCESS);
                resDTO.setMessage("Success");
                resDTO.setContent(smartBinDTO);
                return new ResponseEntity(resDTO, HttpStatus.ACCEPTED);

            }else if(res.equals("01")) {
                resDTO.setCode(VarList.RSP_DUPLICATED);
                resDTO.setMessage("Not A Registered Employee");
                resDTO.setContent(smartBinDTO);
                return new ResponseEntity(resDTO, HttpStatus.BAD_REQUEST);
            }else {
                resDTO.setCode(VarList.RSP_FAIL);
                resDTO.setMessage("Error");
                resDTO.setContent(null);
                return new ResponseEntity(resDTO, HttpStatus.BAD_REQUEST);
            }

        }catch (Exception ex){
            resDTO.setCode(VarList.RSP_ERROR);
            resDTO.setMessage(ex.getMessage());
            resDTO.setContent(null);
            return new ResponseEntity(resDTO, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @GetMapping("/getAllSmartbin")
    public ResponseEntity getAllSmartbin(){
        try {
            List<SmartBinDTO> smartbinlist = smartBinService.getAllSmartbin();
            resDTO.setCode(VarList.RSP_SUCCESS);
            resDTO.setMessage("Success");
            resDTO.setContent(smartbinlist);
            return new ResponseEntity(resDTO, HttpStatus.ACCEPTED);

        }catch (Exception ex){
            resDTO.setCode(VarList.RSP_ERROR);
            resDTO.setMessage(ex.getMessage());
            resDTO.setContent(null);
            return new ResponseEntity(resDTO, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
    @DeleteMapping("/deleteSmartbin/{BinID}")
    public ResponseEntity deleteSmartbin(@PathVariable int BinID){
        try {
            String res = smartBinService.deleteSmartbin(BinID);
            if (res.equals("00")) {
                resDTO.setCode(VarList.RSP_SUCCESS);
                resDTO.setMessage("Success");
                resDTO.setContent(null);
                return new ResponseEntity(resDTO, HttpStatus.ACCEPTED);
            } else {
                resDTO.setCode(VarList.RSP_NO_DATA_FOUND);
                resDTO.setMessage("No Employee Available For this empID");
                resDTO.setContent(null);
                return new ResponseEntity(resDTO, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            resDTO.setCode(VarList.RSP_ERROR);
            resDTO.setMessage(e.getMessage());
            resDTO.setContent(e);
            return new ResponseEntity(resDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllNOTFilledSmartbin")
    public ResponseEntity getNOTfilledSmartBin(){
        try {
            List<SmartBinDTO> NOTfilledSmartBin = smartBinService.getNOTfilledSmartBin();
            resDTO.setCode(VarList.RSP_SUCCESS);
            resDTO.setMessage("Success");
            resDTO.setContent(NOTfilledSmartBin);
            return new ResponseEntity(resDTO, HttpStatus.ACCEPTED);

        }catch (Exception ex){
            resDTO.setCode(VarList.RSP_ERROR);
            resDTO.setMessage(ex.getMessage());
            resDTO.setContent(null);
            return new ResponseEntity(resDTO, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getAllFilledSmartbin")
    public ResponseEntity getfilledSmartBin(){
        try {
            List<SmartBinDTO> filledSmartBin = smartBinService.getfilledSmartBin();
            resDTO.setCode(VarList.RSP_SUCCESS);
            resDTO.setMessage("Success");
            resDTO.setContent(filledSmartBin);
            return new ResponseEntity(resDTO, HttpStatus.ACCEPTED);

        }catch (Exception ex){
            resDTO.setCode(VarList.RSP_ERROR);
            resDTO.setMessage(ex.getMessage());
            resDTO.setContent(null);
            return new ResponseEntity(resDTO, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/updateDistance")
    public String updateDistance(@RequestParam int binId, @RequestParam Long distance) {
        try {
            smartBinService.updateDistance(1, distance);
            return "Distance updated successfully.";
        } catch (Exception e) {
            return "Error updating distance: " + e.getMessage();
        }
    }

}

