package Console.Exeptions;

public class NoSuchIdExeption extends RuntimeException {
    public NoSuchIdExeption() {
        super("ERROR. Wrong ID");
    }
}
