package Console.Exeptions;

public class NoArgumentFoundExeption extends RuntimeException {
    public NoArgumentFoundExeption() {
        super("Argument not found");
    }

    public NoArgumentFoundExeption(String message) {
        super(message);
    }
}
