package com.example.DemoRequest.Controller;

import com.example.DemoRequest.dto.RequestDTO;
import com.example.DemoRequest.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/user")
@CrossOrigin
public class RequestController {
    @Autowired
    private RequestService requestService;

    @GetMapping("/getRequests")
    public List<RequestDTO> getRequest() {
        return requestService.getALLRequest();
    }

    @PostMapping("/saveRequest")
    public RequestDTO saveRequest(@RequestBody RequestDTO requestDTO) {
        return requestService.saveRequest(requestDTO);
    }

    @PutMapping("/updateRequest")
    public RequestDTO updateRequest(@RequestBody RequestDTO requestDTO) {
        return requestService.updateRequest(requestDTO);
    }

    @DeleteMapping("/deleteRequest")
    public boolean deleteRequest(@RequestBody RequestDTO requestDTO){
        return requestService.deleteRequest(requestDTO);
    }

    @GetMapping("/getrequestByRequestID/{requestID}")
    public RequestDTO getRequestByRequestID(@PathVariable String requestID){
       return requestService.getRequestBYRequestID(requestID);
    }
    @GetMapping("/getRequestByRequestIDAndCnumber/{requestID}/{Cnumber}")
    public RequestDTO getRequestByRequestIDAndCnumber(@PathVariable String requestID, @PathVariable Long Cnumber){
        return requestService.getRequestByRequestIDAndCnumber(requestID, Cnumber);
    }

}
