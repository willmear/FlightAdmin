package ExceptionHandling;

public class FlightNotFoundException extends RuntimeException {

    public FlightNotFoundException(Long id) {
        super("Could not find flight " + id);
    }
}
