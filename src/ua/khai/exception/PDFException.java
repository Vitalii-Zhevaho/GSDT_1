package ua.khai.exception;

/**
 * Exception will be thrown if there are some exception with PDF-file.
 *
 * @author Vladyslav Plotnikov
 */
public class PDFException extends AppException {

    private static final long serialVersionUID = 972428660572369897L;

    public PDFException() {
	super();
    }

    public PDFException(String message, Throwable cause) {
	super(message, cause);
    }

    public PDFException(String message) {
	super(message);
    }

    public PDFException(Throwable cause) {
	super(cause);
    }

}
