package console.exсeptions;

/**
 * Исключение, выбрасываемое, если введенная пользователем команда не существует
 */
public class NoSuchCommandException extends RuntimeException {

    public NoSuchCommandException() {
        super("No such command");
    }
    public NoSuchCommandException(String message) {
        super(message + " is not a command");
    }
}
