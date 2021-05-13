package console.commands;

import collectionManager.ArrayListManager;
import console.exсeptions.NoArgumentFoundException;
import musicband.MusicBand;

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
     * @param firstArgument
     * @param requestedMusicBand
     * @return CommandCode.DEFAULT
     */
    @Override
    public CommandCode execute(String firstArgument, MusicBand requestedMusicBand) {
        try {
            long id = Long.parseLong(firstArgument.trim().split(" ")[0]);
            listManager.removeById(id);
        } catch (NumberFormatException e) {
            throw new NoArgumentFoundException();
        }
        return CommandCode.DEFAULT;
    }
}
