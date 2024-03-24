package com.EWT.DisposalPlace.RequestingServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/disposal-Places")
public class DisposalPlaceController {
    @Autowired
    private DisposalPlaceRepository disposalPlaceRepository;

    @GetMapping
    public List<DisposalPlace> getALLDisposalPlaces(){
        return disposalPlaceRepository.findAll();
    }
    /*@PostMapping
    public DisposalPlace createDisposalPlace(@RequestBody DisposalPlace disposalPlace){
        return disposalPlace.save(disposalPlace);
    }*/

    @GetMapping("/smart-bins")
    public int getSmartBinsCount() {
        return 31;
    }
}
