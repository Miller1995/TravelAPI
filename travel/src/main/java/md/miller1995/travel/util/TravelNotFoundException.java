package md.miller1995.travel.util;

public class TravelNotFoundException extends RuntimeException{
    public TravelNotFoundException() {
        super();
    }

    public TravelNotFoundException(String message) {
        super(message);
    }

    public TravelNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
