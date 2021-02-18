package Console.commands;

import CollectionManager.ArrayListManager;
import Console.Console;
import Console.Reader;
import musicband.Coordinates;
import musicband.Label;
import musicband.MusicBand;
import musicband.MusicGenre;

import java.time.LocalDate;
import java.util.ArrayList;

public class AddCommand extends AbstractCommand {

    private ArrayListManager listManager;
    private Reader reader;

    public AddCommand(ArrayListManager listManager, Reader reader) {
        super("add {element}", "добавить новый элемент в коллекцию");
        this.listManager = listManager;
        this.reader = reader;
    }

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
