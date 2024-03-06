/*package com.EWT.DisposalPlace.controller;

import org.springframework.ui.Model;
import com.EWT.DisposalPlace.entity.DisposalPlace;
import com.EWT.DisposalPlace.repo.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MapController {

    @Autowired
    private LocationRepository locationRepository;

    @GetMapping("/showMap")
    public String index(Model model) {
        List<DisposalPlace> locations = locationRepository.findAll();

        StringBuilder dataString = new StringBuilder("[['Lat', 'Long', 'Name']");

        for (DisposalPlace disposalPlace : locations) {
            dataString.append(String.format(", [%s, %s, '%s']", disposalPlace.getLatitude(), disposalPlace.getLongitude(), disposalPlace.getLocation_id()));
        }

        dataString.append("]");

        model.addAttribute("dataString", dataString.toString());

        return "index";
    }
}
*/