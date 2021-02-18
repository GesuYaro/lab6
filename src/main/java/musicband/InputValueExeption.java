package musicband;

public class InputValueExeption extends RuntimeException {
    public InputValueExeption(){}
    public InputValueExeption(String message) {
        super(message);
    }
}
