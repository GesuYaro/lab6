package console.exсeptions;

public class IncorrectScriptException extends RuntimeException {
    public IncorrectScriptException() {
        super("Mistake in the script was found. Stopping the script executing.");
    }
}
