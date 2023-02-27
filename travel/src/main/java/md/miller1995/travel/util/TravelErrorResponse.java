package md.miller1995.travel.util;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TravelErrorResponse {

    private String message;
    private Long timestamp;
}
