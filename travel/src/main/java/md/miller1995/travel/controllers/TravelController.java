package md.miller1995.travel.controllers;


import md.miller1995.travel.dto.TravelDTO;
import md.miller1995.travel.models.Travel;
import md.miller1995.travel.services.TravelService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/travels")
public class TravelController {

    private final TravelService travelService;

    @Autowired
    public TravelController(TravelService travelService){
        this.travelService = travelService;
    }

    @GetMapping()
    public List<Travel> getAllTravels(){
        return travelService.findAllTravels();
    }

    // this method find all Travels after nameField (typeTravel, amount, orderNumber, id, startDate and endDate) in ASC order
    @GetMapping("/search/{nameField}")
    public List<Travel> getAllTravelsWithSortBy(@PathVariable("nameField") String nameField){
        return travelService.findTravelsWithSorting(nameField);
    }


    // find Travel after period and field typeTravel
    @GetMapping("/search")
    public List<Travel> getAllTravelsBetweenDateAndSortByTypeTravel(
            @RequestParam(value = "typeTravel") String typeTravel,
            @RequestParam(value = "startDate") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate startDate,
            @RequestParam(value = "endDate") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate endDate){

        return travelService.findAllTravelsWithSortByTypeTravelAndBetweenDate(typeTravel, startDate, endDate);
    }

    // find Travel by type
    @GetMapping("/search/by")
    public List<Travel> findAllTravelsByTypeTravel(@RequestParam(value = "typeTravel") String typeTravel){
        return travelService.findAllTravelByTypeTravel(typeTravel) ;
    }

    // adding new Travel in database
    @PostMapping("/add")
    public ResponseEntity<HttpStatus> registerTravel(@RequestBody TravelDTO travelDTO){

        Travel travel = travelDTO.convertTravelDTOToTravel();
        travelService.saveTravel(travel);

        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    // delete Travel by id
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTravel(@PathVariable("id") Long id){

        travelService.deleteTravel(id);

        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }

    // Update Travel
    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> updateTravel(@RequestBody TravelDTO travelDTO,
                                                   @PathVariable("id") Long id){

        Travel travel = travelDTO.convertTravelDTOToTravel();

        travelService.updateTravel(id, travel);

        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }


}
