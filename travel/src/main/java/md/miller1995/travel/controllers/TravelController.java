package md.miller1995.travel.controllers;


import md.miller1995.travel.dto.TravelDTO;
import md.miller1995.travel.models.Travel;
import md.miller1995.travel.services.TravelService;
import md.miller1995.travel.util.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
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

    @GetMapping("/{id}")
    public TravelDTO getTravel(@PathVariable("id") Long id){
        Travel travel = travelService.findOneTravel(id);
        return travel.convertTravelToTravelDTO();
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
    public ResponseEntity<HttpStatus> registerTravel(@RequestBody @Valid TravelDTO travelDTO,
                                                     BindingResult bindingResult){
        if (bindingResult.hasErrors()){

            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> errorsList =  bindingResult.getFieldErrors();

            for (FieldError error: errorsList){
                errorMessage.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage() == null ? error.getCode() : error.getDefaultMessage())
                        .append(";");
            }
            throw new TravelNotCreatedException(errorMessage.toString());
        }

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

    // update Travel
    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> updateTravel(@RequestBody TravelDTO travelDTO,
                                                   @PathVariable("id") Long id){

        Travel travel = travelDTO.convertTravelDTOToTravel();

        travelService.updateTravel(id, travel);

        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

    @ExceptionHandler
    private ResponseEntity<TravelErrorResponse> handleTravelNotFoundException(TravelNotFoundException exception){
        TravelErrorResponse response = new TravelErrorResponse(
                                            "Travel with this id wasn't found!",
                                            System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<TravelErrorResponse> handleTravelNotCreatedException(TravelNotCreatedException exception){
        TravelErrorResponse response = new TravelErrorResponse(
                                                exception.getMessage(),
                                                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response,HttpStatus.NOT_ACCEPTABLE);
    }


}
