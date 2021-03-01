package Console.commands;

import CollectionManager.ArrayListManager;
import Console.Exсeptions.NoArgumentFoundException;

/**
 * Класс команды remove_by_id, удаляющей объект по его id
 */
public class RemoveByIdCommand extends AbstractCommand {

    private ArrayListManager listManager;

    /**
     * @param listManager Менеджер коллекции
     */
    public RemoveByIdCommand(ArrayListManager listManager) {
        super("remove_by_id id", "remove an item from the collection by its id");
        this.listManager = listManager;
    }

    /**
     * @param argument
     * @return CommandCode.DEFAULT
     */
    @Override
    public CommandCode execute(String argument) {
        try {
            long id = Long.parseLong(argument.trim().split(" ")[0]);
            listManager.removeById(id);
        } catch (NumberFormatException e) {
            throw new NoArgumentFoundException();
        }
        return CommandCode.DEFAULT;
    }
}
