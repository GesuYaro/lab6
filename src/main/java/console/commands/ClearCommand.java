package console.commands;

import collectionManager.ArrayListManager;

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
     * @param firstArgument
     * @param arguments
     * @return CommandCode.DEFAULT
     */
    @Override
    public CommandCode execute(String firstArgument, String[] arguments) {
        listManager.clear();
        return CommandCode.DEFAULT;
    }
}
