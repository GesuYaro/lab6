package console.exсeptions;

public class NotEnoughArgumentsException extends RuntimeException {
    public NotEnoughArgumentsException() {
        super("Error. Not enough arguments for this command");
    }
}
