package Console.Exeptions;

public class NoSuchCommandExeption extends RuntimeException {

    public NoSuchCommandExeption() {
        super("No such command");
    }
    public NoSuchCommandExeption(String message) {
        super(message + " is not a command");
    }
}
