package console.commands;

import collectionManager.ArrayListManager;
import console.exсeptions.InputValueException;
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
     * @param arguments
     * @return CommandCode.DEFAULT
     * @throws InputValueException
     */
    @Override
    public CommandCode execute(String firstArgument, String[] arguments) throws InputValueException {
        if (arguments.length < 7) throw new NotEnoughArgumentsException();
        String name = checker.readName(arguments[0]);
        Coordinates coordinates = new Coordinates(checker.readX(arguments[1]), checker.readY(arguments[2]));
        LocalDate creationDate = LocalDate.now();
        int numberOfParticipants = checker.readNumberOfParticipants(arguments[3]);
        Integer singlesCount = checker.readSinglesCount(arguments[4]);
        MusicGenre musicGenre = checker.readMusicGenre(arguments[5]);
        Label label = checker.readLabel(arguments[6]);
        listManager.increaseMaxId();
        long id = listManager.getMaxId();
        MusicBand musicBand = new MusicBand(id, name, coordinates, creationDate, numberOfParticipants, singlesCount, musicGenre, label);
        listManager.add(musicBand);
        return CommandCode.DEFAULT;
    }
}
