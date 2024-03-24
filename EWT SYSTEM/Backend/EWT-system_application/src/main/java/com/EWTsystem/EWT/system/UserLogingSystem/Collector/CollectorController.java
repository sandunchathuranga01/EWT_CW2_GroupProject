package com.EWTsystem.EWT.system.UserLogingSystem.Collector;

import com.EWTsystem.EWT.system.Other.ResDTO;
import com.EWTsystem.EWT.system.Other.StandardResponse;
import com.EWTsystem.EWT.system.Other.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("api/v1/collector")
public class CollectorController {

    @Autowired
    private ResDTO resDTO;

    @Autowired
    private CollectorRepo collectorRepo;

    @Autowired
    private CollectorService collectorService;

    @Autowired
    private REGCollectorRepo regCollectorRepo;



//loging to use REGCollector table
    @GetMapping("/login")
    public ResponseEntity<StandardResponse> authorizedUser(@RequestParam(value = "email") String username,
                                                           @RequestParam(value = "pws") String password){
        try {
            if (username == null || password == null) {
                return new ResponseEntity<>(
                        new StandardResponse(401, "Login failed: username or password is empty", null),
                        HttpStatus.BAD_REQUEST);
            }

            List<REGCollector> REGcollectors =regCollectorRepo.findAllByEmail(username);

            if (REGcollectors.isEmpty()) {
                return new ResponseEntity<>(
                        new StandardResponse(404, "Login failed: User not found", null),
                        HttpStatus.NOT_FOUND);
            }
            REGCollector regCollector= REGcollectors.get(0);


            if (!regCollector.getPassword().equals(password)) {
                return new ResponseEntity<>(
                        new StandardResponse(401, "Login failed: Incorrect password", null),
                        HttpStatus.UNAUTHORIZED);
            }

            // User authenticated successfully, construct and return response
            REGCollectorDTO regCollectorDTO = new REGCollectorDTO();
            regCollectorDTO.setId(regCollector.getId());
            regCollectorDTO.setAddress(regCollector.getAddress());
            regCollectorDTO.setFirstname(regCollector.getFirstname());
            regCollectorDTO.setLastname(regCollector.getLastname());
            regCollectorDTO.setEmail(regCollector.getEmail());
            regCollectorDTO.setContactnumber(regCollector.getContactnumber());

            return new ResponseEntity<>(
                    new StandardResponse(200, "Login successful", regCollectorDTO),
                    HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(
                    new StandardResponse(500, "Internal Server Error", null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }





 //when the collector register, it used to collector table
    @PostMapping("/saveCollector")
    public ResponseEntity saveCollector(@RequestBody CollectorDTO collectorDTO) {
        try {
            String res = collectorService.saveCollector(collectorDTO);
            if (res.equals("00")) {
                resDTO.setCode(VarList.RSP_SUCCESS);
                resDTO.setMessage("Success");
                resDTO.setContent(collectorDTO);
                return new ResponseEntity(resDTO, HttpStatus.ACCEPTED);
            }
            else if (res.equals("06")) {
                resDTO.setCode(VarList.RSP_DUPLICATED);
                resDTO.setMessage("Disposal place Already Registered");
                resDTO.setContent(collectorDTO);
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

    //Admin collector save as REG collector
    @PostMapping("/saveAsREGCollector")
    public ResponseEntity saveAsREGCollector(@RequestBody REGCollectorDTO regCollectorDTO) {
        try {
            String res = collectorService.saveAsREGCollector(regCollectorDTO);
            if (res.equals("00")) {
                resDTO.setCode(VarList.RSP_SUCCESS);
                resDTO.setMessage("Success");
                resDTO.setContent(regCollectorDTO);
                return new ResponseEntity(resDTO, HttpStatus.ACCEPTED);
            }
            else if (res.equals("06")) {
                resDTO.setCode(VarList.RSP_DUPLICATED);
                resDTO.setMessage("Disposal place Already Registered");
                resDTO.setContent(regCollectorDTO);
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




//to update own data,to used REGClient table
    @PutMapping(value = "/updateCollector")
    public ResponseEntity updateCollector(@RequestBody CollectorDTO collectorDTO){
        try {
            String res=collectorService.updateCollector(collectorDTO);
            if (res.equals("00")){
                resDTO.setCode(VarList.RSP_SUCCESS);
                resDTO.setMessage("Success");
                resDTO.setContent(collectorDTO);
                return new ResponseEntity(resDTO, HttpStatus.ACCEPTED);

            }else if(res.equals("01")) {
                resDTO.setCode(VarList.RSP_DUPLICATED);
                resDTO.setMessage("Not A Registered Employee");
                resDTO.setContent(collectorDTO);
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
//so getAllCollector used to show collector request
    @GetMapping("/getAllCollector")
    public ResponseEntity getAllCollector(){
        try {
            List<CollectorDTO> CollectorList = collectorService.getAllCollector();
            resDTO.setCode(VarList.RSP_SUCCESS);
            resDTO.setMessage("Success");
            resDTO.setContent(CollectorList);
            return new ResponseEntity(resDTO, HttpStatus.ACCEPTED);

        }catch (Exception ex){
            resDTO.setCode(VarList.RSP_ERROR);
            resDTO.setMessage(ex.getMessage());
            resDTO.setContent(null);
            return new ResponseEntity(resDTO, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    //so getAllCollector used to show collector request
    @GetMapping("/getAllREGCollector")
    public ResponseEntity getAllREGCollector(){
        try {
            List<REGCollectorDTO> REGCollectorList = collectorService.getAllREGCollector();
            resDTO.setCode(VarList.RSP_SUCCESS);
            resDTO.setMessage("Success");
            resDTO.setContent(REGCollectorList);
            return new ResponseEntity(resDTO, HttpStatus.ACCEPTED);

        }catch (Exception ex){
            resDTO.setCode(VarList.RSP_ERROR);
            resDTO.setMessage(ex.getMessage());
            resDTO.setContent(null);
            return new ResponseEntity(resDTO, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

//delete collector form Collector table
    @DeleteMapping("/deleteCollectorFromCollectorDataTable/{id}")
    public ResponseEntity deleteCollector(@PathVariable int id){
        try {
            String res = collectorService.deleteCollector(id);
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

    //delete collector form Collector table
    @DeleteMapping("/deleteCollectorFromREGCollectorDataTable/{id}")
    public ResponseEntity deleteREGCollector(@PathVariable int id){
        try {
            String res = collectorService.deleteREGCollector(id);
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





