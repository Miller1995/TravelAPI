package md.miller1995.travel.util;

public class TravelNotCreatedException extends RuntimeException{
    public TravelNotCreatedException() {
    }

    public TravelNotCreatedException(String message) {
        super(message);
    }

    public TravelNotCreatedException(String message, Throwable cause) {
        super(message, cause);
    }
}
