package md.miller1995.travel.controllers;

import md.miller1995.travel.models.Travel;
import md.miller1995.travel.services.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/travel")
public class TravelController {

    private final TravelService travelService;

    @Autowired
    public TravelController(TravelService travelService) {
        this.travelService = travelService;
    }

    @GetMapping()
    public List<Travel> getAllTravels(){
        return travelService.findAllTravels();
    }
}
