package Console.commands;

import CollectionManager.ArrayListManager;
import Console.Exeptions.NoArgumentFoundExeption;

public class RemoveByIdCommand extends AbstractCommand {

    private ArrayListManager listManager;

    public RemoveByIdCommand(ArrayListManager listManager) {
        super("remove_by_id id", "remove an item from the collection by its id");
        this.listManager = listManager;
    }

    @Override
    public CommandCode execute(String argument) {
        try {
            long id = Long.parseLong(argument.trim().split(" ")[0]);
            listManager.removeById(id);
            return CommandCode.DEFAULT;
        } catch (NumberFormatException e) {
            throw new NoArgumentFoundExeption();
        }
    }
}
