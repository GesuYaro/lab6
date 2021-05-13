package client.exceptions;

public class NotAResponseException extends RuntimeException {
    public NotAResponseException() {
        super("Not a response");
    }
}
