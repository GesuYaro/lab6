package Console.commands;

import CollectionManager.ArrayListManager;

public class RemoveByIdCommand extends AbstractCommand {

    private ArrayListManager listManager;

    public RemoveByIdCommand(ArrayListManager listManager) {
        super("remove_by_id id", "удалить элемент из коллекции по его id");
        this.listManager = listManager;
    }

    @Override
    public CommandCode execute(String argument) {
        long id = Long.parseLong(argument.trim().split(" ")[0]);
        listManager.removeById(id);
        return CommandCode.DEFAULT;
    }
}
