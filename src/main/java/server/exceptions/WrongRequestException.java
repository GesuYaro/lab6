package server.exceptions;

public class WrongRequestException extends RuntimeException {

    public WrongRequestException() {
        super();
    }

    public WrongRequestException(String message) {
        super(message);
    }

}
