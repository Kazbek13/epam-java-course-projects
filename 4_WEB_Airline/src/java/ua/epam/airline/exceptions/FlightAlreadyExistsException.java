package ua.epam.airline.exceptions;

/**
 *
 * @author Andrew
 */
public class FlightAlreadyExistsException extends Exception {

    public FlightAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
    
    @Override
    public Throwable getCause() {
        return super.getCause();
    }
}
