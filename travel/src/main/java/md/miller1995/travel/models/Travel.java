package md.miller1995.travel.models;


import lombok.*;
import md.miller1995.travel.dto.TravelDTO;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Travel")
@Setter
@Getter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Travel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type_travel")
    private String typeTravel;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "order_number")
    private String orderNumber;

    public TravelDTO convertTravelToTravelDTO(){
        return new ModelMapper().map(this, TravelDTO.class);
    }
}
