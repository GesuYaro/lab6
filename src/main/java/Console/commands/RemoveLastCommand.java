package Console.commands;

import CollectionManager.ArrayListManager;

public class RemoveLastCommand extends AbstractCommand {
    private ArrayListManager listManager;
    public RemoveLastCommand(ArrayListManager listManager) {
        super("remove_last", "удалить последний элемент из коллекции");
        this.listManager = listManager;
    }

    @Override
    public CommandCode execute(String argument) {
        listManager.removeLast();
        return CommandCode.DEFAULT;
    }
}
