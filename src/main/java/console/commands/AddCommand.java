package console.commands;

import collectionManager.ArrayListManager;
import console.Reader;
import musicband.Coordinates;
import musicband.Label;
import musicband.MusicBand;
import musicband.MusicGenre;

import java.time.LocalDate;


/**
 * Класс команды add, добавляющей элемент в коллекцию
 */
public class AddCommand extends AbstractCommand {

    private ArrayListManager listManager;
    private Reader reader;

    /**
     * @param listManager Менеджер коллекции
     * @param reader Считыватель полей элемента коллекции
     */
    public AddCommand(ArrayListManager listManager, Reader reader) {
        super("add {element}", "add new element into collection");
        this.listManager = listManager;
        this.reader = reader;
    }

    /**
     * @param argument
     * @return CommandCode.DEFAULT
     */
    @Override
    public CommandCode execute(String argument) {
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
        listManager.add(musicBand);
        return CommandCode.DEFAULT;
    }
}
