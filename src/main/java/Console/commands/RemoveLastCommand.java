package Console.commands;

import CollectionManager.ArrayListManager;

/**
 * Класс команды remove_last, удаляющей последний элемент
 */
public class RemoveLastCommand extends AbstractCommand {
    private ArrayListManager listManager;

    /**
     * @param listManager Менеджер коллекции
     */
    public RemoveLastCommand(ArrayListManager listManager) {
        super("remove_last", "remove the last item from the collection");
        this.listManager = listManager;
    }

    /**
     * @param argument
     * @return CommandCode.DEFAULT;
     */
    @Override
    public CommandCode execute(String argument) {
        listManager.removeLast();
        return CommandCode.DEFAULT;
    }
}
