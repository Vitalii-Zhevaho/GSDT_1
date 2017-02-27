package ua.khai.exception;

/**
 * Exception will be thrown if there are some exception with CSV-file.
 *
 * @author Vladyslav Plotnikov
 */
public class CSVException extends AppException {

    private static final long serialVersionUID = 5494151627559165270L;

    public CSVException() {
	super();
    }

    public CSVException(String message, Throwable cause) {
	super(message, cause);
    }

    public CSVException(String message) {
	super(message);
    }

    public CSVException(Throwable cause) {
	super(cause);
    }

}
