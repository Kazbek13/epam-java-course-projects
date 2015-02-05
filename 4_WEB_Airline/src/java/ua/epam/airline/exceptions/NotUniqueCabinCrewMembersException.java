package ua.epam.airline.exceptions;

/**
 *
 * @author Andrew
 */
public class NotUniqueCabinCrewMembersException extends Exception {
    public NotUniqueCabinCrewMembersException(String message, Throwable cause) {
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
