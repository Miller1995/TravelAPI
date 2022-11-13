package md.miller1995.travel.repositories;

import md.miller1995.travel.models.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TravelRepository extends JpaRepository<Travel,Long>{

    // custom query for find all Travels with typeTravel=? and startDate=>? and endDate<=?
    List<Travel> findAllByTypeTravelAndStartDateGreaterThanEqualAndEndDateLessThanEqual(String typeTravel,
                                                                                        LocalDate startDate,
                                                                                        LocalDate endDate);

    List<Travel> findAllByTypeTravel(String typeTravel);

}

