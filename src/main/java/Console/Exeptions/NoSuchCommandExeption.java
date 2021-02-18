package Console.Exeptions;

public class NoSuchCommandExeption extends RuntimeException {

    public NoSuchCommandExeption() {
        super("Нет такой команды");
    }
    public NoSuchCommandExeption(String message) {
        super(message + " не является командой");
    }
}
