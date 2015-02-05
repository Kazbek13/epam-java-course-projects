package ua.epam.airline.exceptions;

/**
 *
 * @author Andrew
 */
public class ShuttleFlightAlreadyExistsException extends Exception {
    public ShuttleFlightAlreadyExistsException(String message, Throwable cause) {
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
