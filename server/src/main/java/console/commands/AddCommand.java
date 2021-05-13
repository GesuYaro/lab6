package console.commands;

import collectionManager.ArrayListManager;
import musicband.InputValueException;
import console.exсeptions.NotEnoughArgumentsException;
import musicband.*;

import java.time.LocalDate;


/**
 * Класс команды add, добавляющей элемент в коллекцию
 */
public class AddCommand extends AbstractCommand {

    private ArrayListManager listManager;
    private MusicBandFieldsChecker checker;

    /**
     * @param listManager Менеджер коллекции
     * @param reader Считыватель полей элемента коллекции
     */
    public AddCommand(ArrayListManager listManager, MusicBandFieldsChecker reader) {
        super("add {element}", "add new element into collection");
        this.listManager = listManager;
        this.checker = reader;
    }

    /**
     * @param firstArgument
     * @param requestedMusicBand
     * @return CommandCode.DEFAULT
     * @throws InputValueException
     */
    @Override
    public CommandCode execute(String firstArgument, MusicBand requestedMusicBand) throws InputValueException {
        if (requestedMusicBand == null) throw new NotEnoughArgumentsException();
        LocalDate creationDate = LocalDate.now();
        listManager.increaseMaxId();
        long id = listManager.getMaxId();
        requestedMusicBand.setCreationDate(creationDate);
        requestedMusicBand.setId(id);
        listManager.add(requestedMusicBand);
        return CommandCode.DEFAULT;
    }
}
