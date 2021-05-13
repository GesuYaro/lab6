package musicband;

/**
 * Исключение, выбрасываемое при неправильном вводе значения
 */
public class InputValueException extends RuntimeException {
    public InputValueException(){}
    public InputValueException(String message) {
        super(message);
    }
}
