package md.miller1995.travel.services;

import md.miller1995.travel.models.Travel;
import md.miller1995.travel.repositories.TravelRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class TravelService {

    private final TravelRepository travelRepository;

    @Autowired
    public TravelService(TravelRepository travelRepository){
        this.travelRepository = travelRepository;
    }

    public List<Travel> findAllTravels(){
        return travelRepository.findAll();
    }

    public List<Travel> findTravelsWithSorting(String nameField){
        return travelRepository.findAll(Sort.by(Sort.Direction.ASC, nameField));
    }

    public List<Travel> findAllTravelsWithSortByTypeTravelAndBetweenDate(String typeTravel,
                                                                       LocalDate startDate,
                                                                       LocalDate endDate){
        return travelRepository.findAllByTypeTravelAndStartDateGreaterThanEqualAndEndDateLessThanEqual( typeTravel,
                                                                                                        startDate,
                                                                                                        endDate);
    }

    public List<Travel> findAllTravelByTypeTravel(String typeTravel){
        return travelRepository.findAllByTypeTravel(typeTravel);
    }

    @Transactional
    public void saveTravel(Travel travel){
        travelRepository.save(travel);
    }

    @Transactional
    public void deleteTravel(Long id){
        travelRepository.deleteById(id);
    }
}
