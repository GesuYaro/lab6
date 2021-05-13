package console.commands;

import collectionManager.ArrayListManager;
import musicband.InputValueException;
import console.exсeptions.NoArgumentFoundException;
import musicband.*;

import java.time.LocalDate;

/**
 * Класс команды insert_at, добаляющего новый объект в заданную позицию
 */
public class InsertAtCommand extends AbstractCommand {

    private ArrayListManager listManager;
    private MusicBandFieldsChecker fieldsChecker;

    /**
     * @param listManager Менеджер коллекции
     * @param fieldsChecker Считыватель полей
     */
    public InsertAtCommand(ArrayListManager listManager, MusicBandFieldsChecker fieldsChecker) {
        super("insert_at index {element}", "add a new item at a given position");
        this.listManager = listManager;
        this.fieldsChecker = fieldsChecker;
    }

    /**
     * @param firstArgument id элемента
     * @param requestedMusicBand
     * @return CommandCode.DEFAULT
     */
    @Override
    public CommandCode execute(String firstArgument, MusicBand requestedMusicBand) throws InputValueException, IndexOutOfBoundsException, NoArgumentFoundException {
        try {
            int index = Integer.parseInt(firstArgument
                    .trim()
                    .split(" ")[0]
            );
            if ( !(index > listManager.getArrayList().size() - 1 || index < 0) ) {
                LocalDate creationDate = LocalDate.now();
                listManager.increaseMaxId();
                long id = listManager.getMaxId();
                requestedMusicBand.setId(id);
                requestedMusicBand.setCreationDate(creationDate);
                listManager.insertAtIndex(index, requestedMusicBand);
            } else {
                throw new IndexOutOfBoundsException();
            }
        } catch (NumberFormatException e) {
            throw new NoArgumentFoundException();
        }
        return CommandCode.DEFAULT;
    }
}
