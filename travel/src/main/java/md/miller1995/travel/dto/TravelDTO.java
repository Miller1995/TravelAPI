package md.miller1995.travel.dto;

import lombok.*;
import md.miller1995.travel.models.Travel;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class TravelDTO{
    private String typeTravel;
    private String placeTravel;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double amount;



    public Travel convertTravelDTOToTravel(){
        return new ModelMapper().map(this, Travel.class);
    }
}


