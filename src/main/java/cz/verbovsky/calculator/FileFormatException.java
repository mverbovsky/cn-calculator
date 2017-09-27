package cz.verbovsky.calculator;

/**
 * Thrown to indicate that the file has an appropriate format.
 *
 * @author Martin Verbovsky
 */
public class FileFormatException extends IllegalArgumentException {

    public FileFormatException() {
        super();
    }

    public FileFormatException(String message) {
        super(message);
    }

    public FileFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
