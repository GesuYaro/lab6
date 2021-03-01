package Console.Exсeptions;

/**
 * Исключение, выбрасываемое, если пользователь ввел неверный id
 */
public class NoSuchIdException extends RuntimeException {
    public NoSuchIdException() {
        super("ERROR. Wrong ID");
    }
}
