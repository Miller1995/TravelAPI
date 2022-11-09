package md.miller1995.travel.services;

import md.miller1995.travel.models.Travel;
import md.miller1995.travel.repositories.TravelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravelService {

    private final TravelRepository travelRepository;

    @Autowired
    public TravelService(TravelRepository travelRepository) {
        this.travelRepository = travelRepository;
    }

    public List<Travel> findAllTravels() {
        return travelRepository.findAll();
    }
}