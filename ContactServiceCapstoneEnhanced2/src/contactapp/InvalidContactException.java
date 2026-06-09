package contactapp;

/**
 * Custom unchecked exception used to indicate that a Contact
 * has been created or updated with invalid data.
 */
public class InvalidContactException extends RuntimeException {

    public InvalidContactException(String message) {
        super(message);
    }

    public InvalidContactException(String message, Throwable cause) {
        super(message, cause);
    }
}
