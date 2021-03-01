package Console.commands;

import CollectionManager.ArrayListManager;

/**
 * Класс команды clear, очищающей коллекцию
 */
public class ClearCommand extends AbstractCommand {
    private ArrayListManager listManager;

    /**
     * @param listManager Менеджер коллекции
     */
    public ClearCommand(ArrayListManager listManager) {
        super("clear", "clear collection");
        this.listManager = listManager;
    }

    /**
     * @param argument
     * @return CommandCode.DEFAULT
     */
    @Override
    public CommandCode execute(String argument) {
        listManager.clear();
        return CommandCode.DEFAULT;
    }
}
