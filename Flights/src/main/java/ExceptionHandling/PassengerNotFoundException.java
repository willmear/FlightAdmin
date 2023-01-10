package ExceptionHandling;

public class PassengerNotFoundException extends RuntimeException{

    public PassengerNotFoundException(Long id) {
        super("Could not find passenger: " + id);

    }
}
