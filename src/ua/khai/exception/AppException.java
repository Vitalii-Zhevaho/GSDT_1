package ua.khai.exception;

/**
 * Application exception. Root of all application exceptions.
 *
 * @author Vladyslav Plotnikov
 */
public class AppException extends RuntimeException {

    private static final long serialVersionUID = -4145442193874710310L;

    public AppException() {
	super();
    }

    public AppException(String message, Throwable cause) {
	super(message, cause);
    }

    public AppException(String message) {
	super(message);
    }

    public AppException(Throwable cause) {
	super(cause);
    }

}
