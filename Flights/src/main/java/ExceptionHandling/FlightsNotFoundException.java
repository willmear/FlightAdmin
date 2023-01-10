package ExceptionHandling;

import org.springframework.data.crossstore.ChangeSetPersister;

public class FlightsNotFoundException extends RuntimeException {
    public FlightsNotFoundException(String destination) {
        super("No flight found to destination: " + destination);
    }
}
