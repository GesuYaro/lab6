package console.exсeptions;

/**
 * Исключение, выбрасываемое при ненахождении необходимого аргумента
 */
public class NoArgumentFoundException extends RuntimeException {
    public NoArgumentFoundException() {
        super("Argument not found");
    }

    public NoArgumentFoundException(String message) {
        super(message);
    }
}
