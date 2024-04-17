package ee.taltech.inbankbackend.exceptions;

/**
 * Thrown when user is too young or too old to apply for a loan.
 */
public class InvalidPersonalAgeException extends Throwable {
    private final String message;
    private final Throwable cause;

    public InvalidPersonalAgeException(String message) {
        this(message, null);
    }

    public InvalidPersonalAgeException(String message, Throwable cause) {
        this.message = message;
        this.cause = cause;
    }

    @Override
    public Throwable getCause() {
        return cause;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
