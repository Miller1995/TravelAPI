package md.miller1995.travel.dto;

import lombok.*;
import md.miller1995.travel.models.Travel;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class TravelDTO{

    @NotBlank (message = "Type travel can't be empty")
    private String typeTravel;

    @NotBlank (message = "Place travel can't be empty")
    private String placeTravel;

    @NotNull(message = "Start date can't be null and format should be yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @NotNull(message = "end date can't be null and format should be yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @NotNull(message = "Amount can't be null")
    private Double amount;

    public Travel convertTravelDTOToTravel(){
        return new ModelMapper().map(this, Travel.class);
    }
}


