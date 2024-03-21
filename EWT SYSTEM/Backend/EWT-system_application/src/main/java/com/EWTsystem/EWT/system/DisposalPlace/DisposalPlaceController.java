package com.EWTsystem.EWT.system.DisposalPlace;

import com.EWTsystem.EWT.system.Other.ResDTO;
import com.EWTsystem.EWT.system.Other.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/disposal_place")
@CrossOrigin
public class DisposalPlaceController {
    @Autowired
    private DisposalPlaceService disposalPlaceService ;

    @Autowired
    private ResDTO resDTO;

    @PostMapping("/saveDisposalPlace")
    public ResponseEntity saveDisposalPlace(@RequestBody DisposalPlaceDTO disposalPlaceDTO) {
        try {
            String res = disposalPlaceService.saveDisposalPlace(disposalPlaceDTO);
            if (res.equals("00")) {
                resDTO.setCode(VarList.RSP_SUCCESS);
                resDTO.setMessage("Success");
                resDTO.setContent(disposalPlaceDTO);
                return new ResponseEntity(resDTO, HttpStatus.ACCEPTED);
            }
            else if (res.equals("06")) {
                resDTO.setCode(VarList.RSP_DUPLICATED);
                resDTO.setMessage("Disposal place Already Registered");
                resDTO.setContent(disposalPlaceDTO);
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

    @PutMapping(value = "/updateDisposalPlace")
    public ResponseEntity updateDisposalPlace(@RequestBody DisposalPlaceDTO disposalPlaceDTO){
        try {
            String res=disposalPlaceService.updateDisposalPlace(disposalPlaceDTO);
            if (res.equals("00")){
                resDTO.setCode(VarList.RSP_SUCCESS);
                resDTO.setMessage("Success");
                resDTO.setContent(disposalPlaceDTO);
                return new ResponseEntity(resDTO, HttpStatus.ACCEPTED);

            }else if(res.equals("01")) {
                resDTO.setCode(VarList.RSP_DUPLICATED);
                resDTO.setMessage("Not A Registered Employee");
                resDTO.setContent(disposalPlaceDTO);
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

    @GetMapping("/getAllDisposalPlaces")
    public ResponseEntity getAllDisposalPlaces(){
        try {
            List<DisposalPlaceDTO> employeeDTOList = disposalPlaceService.getAllDisposalPlaces();
            resDTO.setCode(VarList.RSP_SUCCESS);
            resDTO.setMessage("Success");
            resDTO.setContent(employeeDTOList);
            return new ResponseEntity(resDTO, HttpStatus.ACCEPTED);

        }catch (Exception ex){
            resDTO.setCode(VarList.RSP_ERROR);
            resDTO.setMessage(ex.getMessage());
            resDTO.setContent(null);
            return new ResponseEntity(resDTO, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @DeleteMapping("/deleteDisposalPlace/{locationID}")
    public ResponseEntity deleteDisposalPlace(@PathVariable int locationID){
        try {
            String res = disposalPlaceService.deleteDisposalPlace(locationID);
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
}

