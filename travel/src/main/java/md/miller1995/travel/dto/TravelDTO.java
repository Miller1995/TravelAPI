package md.miller1995.travel.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class TravelDTO {
    private String typeTravel;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double amount;

}
