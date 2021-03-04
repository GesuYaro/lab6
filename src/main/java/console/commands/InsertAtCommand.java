package console.commands;

import collectionManager.ArrayListManager;
import console.ConsoleWriter;
import console.exсeptions.NoArgumentFoundException;
import console.Reader;
import musicband.Coordinates;
import musicband.Label;
import musicband.MusicBand;
import musicband.MusicGenre;

import java.time.LocalDate;

/**
 * Класс команды insert_at, добаляющего новый объект в заданную позицию
 */
public class InsertAtCommand extends AbstractCommand {

    private ConsoleWriter writer;
    private ArrayListManager listManager;
    private Reader reader;

    /**
     * @param writer Объект класса, выводящего в консоль
     * @param listManager Менеджер коллекции
     * @param reader Считыватель полей
     */
    public InsertAtCommand(ConsoleWriter writer, ArrayListManager listManager, Reader reader) {
        super("insert_at index {element}", "add a new item at a given position");
        this.writer = writer;
        this.listManager = listManager;
        this.reader = reader;
    }

    /**
     * @param argument id элемента
     * @return CommandCode.DEFAULT
     */
    @Override
    public CommandCode execute(String argument) {
        try {
            try {
                int index = Integer.parseInt(argument
                        .trim()
                        .split(" ")[0]
                );
                if ( !(index > listManager.getArrayList().size() - 1 || index < 0) ) {
                    String name = reader.readName();
                    Coordinates coordinates = reader.readCoordinates();
                    LocalDate creationDate = LocalDate.now();
                    int numberOfParticipants = reader.readNumberOfParticipants();
                    Integer singlesCount = reader.readSinglesCount();
                    MusicGenre musicGenre = reader.readMusicGenre();
                    Label label = reader.readlabel();
                    listManager.increaseMaxId();
                    long id = listManager.getMaxId();
                    MusicBand musicBand = new MusicBand(id, name, coordinates, creationDate, numberOfParticipants, singlesCount, musicGenre, label);
                    listManager.insertAtIndex(index, musicBand);
                } else {
                    writer.write("ERROR. Index is out of bounds");
                }
            } catch (IndexOutOfBoundsException e) {
                writer.write("ERROR. Index is out of bounds");
            }
        } catch (NumberFormatException e) {
            throw new NoArgumentFoundException();
        }
        return CommandCode.DEFAULT;
    }
}
