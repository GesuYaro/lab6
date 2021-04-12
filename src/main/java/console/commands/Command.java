package console.commands;

/**
 * Интерфейс для команд
 */
public interface Command {
    CommandCode execute(String firstArgument, String[] arguments);
    String getName();
    String getDescription();
}
