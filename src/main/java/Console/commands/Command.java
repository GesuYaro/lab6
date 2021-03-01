package Console.commands;

/**
 * Интерфейс для команд
 */
public interface Command {
    CommandCode execute(String argument);
    String getName();
    String getDescription();
}
