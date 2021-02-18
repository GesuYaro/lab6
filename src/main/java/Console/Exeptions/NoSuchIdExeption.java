package Console.Exeptions;

public class NoSuchIdExeption extends RuntimeException {
    public NoSuchIdExeption() {
        super("Ошибка. ID введен неверно");
    }
}
