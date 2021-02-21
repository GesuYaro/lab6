package Console.commands;

import CollectionManager.ArrayListManager;

public class RemoveLastCommand extends AbstractCommand {
    private ArrayListManager listManager;
    public RemoveLastCommand(ArrayListManager listManager) {
        super("remove_last", "remove the last item from the collection");
        this.listManager = listManager;
    }

    @Override
    public CommandCode execute(String argument) {
        listManager.removeLast();
        return CommandCode.DEFAULT;
    }
}
