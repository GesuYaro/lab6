package Console.commands;

import CollectionManager.ArrayListManager;

public class ClearCommand extends AbstractCommand {
    private ArrayListManager listManager;
    public ClearCommand(ArrayListManager listManager) {
        super("clear", "clear collection");
        this.listManager = listManager;
    }

    @Override
    public CommandCode execute(String argument) {
        listManager.clear();
        return CommandCode.DEFAULT;
    }
}
