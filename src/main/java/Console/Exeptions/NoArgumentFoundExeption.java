package Console.Exeptions;

public class NoArgumentFoundExeption extends RuntimeException {
    public NoArgumentFoundExeption() {
        super("Аргумент команды не обнаружен");
    }

    public NoArgumentFoundExeption(String message) {
        super(message);
    }
}
