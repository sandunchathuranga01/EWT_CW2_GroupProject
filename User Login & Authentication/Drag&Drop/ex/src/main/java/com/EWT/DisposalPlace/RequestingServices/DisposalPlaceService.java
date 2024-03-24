package com.EWT.DisposalPlace.RequestingServices;
import com.EWT.DisposalPlace.RequestingServices.repository.DisposalPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class DisposalPlaceService {
    private final DisposalPlaceRepository disposalPlaceRepository;

    @Autowired
    public DisposalPlaceService(DisposalPlaceRepository disposalPlaceRepository){
        this.disposalPlaceRepository=disposalPlaceRepository;
    }

    public DisposalPlace saveDisposalPlace(DisposalPlace disposalPlace) {
        return disposalPlaceRepository.save(disposalPlace);
    }
}
