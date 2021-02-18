package Console.commands;

import CollectionManager.ArrayListManager;
import Console.Reader;
import musicband.Coordinates;
import musicband.Label;
import musicband.MusicBand;
import musicband.MusicGenre;

import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;

public class InsertAtCommand extends AbstractCommand {

    private Writer writer;
    private ArrayListManager listManager;
    private Reader reader;

    public InsertAtCommand(Writer writer, ArrayListManager listManager, Reader reader) {
        super("insert_at index {element}", "добавить новый элемент в заданную позицию");
        this.writer = writer;
        this.listManager = listManager;
        this.reader = reader;
    }

    @Override
    public CommandCode execute(String argument) {
        try {
            int index = Integer.parseInt(argument.trim().split(" ")[0]);
            if (Integer.compare(index, listManager.getArrayList().size() - 1) > 0)
                throw new IndexOutOfBoundsException();
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
        }
        catch (IndexOutOfBoundsException e) {
            try {
                writer.write("Ошибка. Индекс больше размера коллекции");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return CommandCode.DEFAULT;
    }
}
