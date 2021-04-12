package console.commands;

import collectionManager.ArrayListManager;
import console.ConsoleWriter;
import console.exсeptions.InputValueException;
import console.exсeptions.NoArgumentFoundException;
import console.Reader;
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
     * @param arguments
     * @return CommandCode.DEFAULT
     */
    @Override
    public CommandCode execute(String firstArgument, String[] arguments) throws InputValueException, IndexOutOfBoundsException, NoArgumentFoundException {
        try {
            try {
                int index = Integer.parseInt(firstArgument
                        .trim()
                        .split(" ")[0]
                );
                if ( !(index > listManager.getArrayList().size() - 1 || index < 0) ) {
                    String name = fieldsChecker.readName(arguments[0]);
                    Coordinates coordinates = new Coordinates(fieldsChecker.readX(arguments[1]), fieldsChecker.readY(arguments[2]));
                    LocalDate creationDate = LocalDate.now();
                    int numberOfParticipants = fieldsChecker.readNumberOfParticipants(arguments[3]);
                    Integer singlesCount = fieldsChecker.readSinglesCount(arguments[4]);
                    MusicGenre musicGenre = fieldsChecker.readMusicGenre(arguments[5]);
                    Label label = fieldsChecker.readLabel(arguments[6]);
                    listManager.increaseMaxId();
                    long id = listManager.getMaxId();
                    MusicBand musicBand = new MusicBand(id, name, coordinates, creationDate, numberOfParticipants, singlesCount, musicGenre, label);
                    listManager.insertAtIndex(index, musicBand);
                } else {
                    throw new IndexOutOfBoundsException();
                }
            } catch (IndexOutOfBoundsException e) {
                throw e;
            }
        } catch (NumberFormatException e) {
            throw new NoArgumentFoundException();
        }
        return CommandCode.DEFAULT;
    }
}
