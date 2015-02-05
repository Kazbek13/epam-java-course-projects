package ua.epam.airline.exceptions;

/**
 *
 * @author Andrew
 */
public class AircraftAlreadyExistsException extends Exception {

    public AircraftAlreadyExistsException(String message, Throwable cause) {
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

