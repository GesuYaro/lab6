package Console.commands;

import CollectionManager.ArrayListManager;
import Console.ConsoleWriter;
import Console.Exсeptions.NoArgumentFoundException;
import Console.Exсeptions.NoSuchIdException;
import Console.Reader;
import musicband.Coordinates;
import musicband.Label;
import musicband.MusicBand;
import musicband.MusicGenre;

import java.time.LocalDate;

public class UpdateCommand extends AbstractCommand {

    private ConsoleWriter writer;
    private ArrayListManager listManager;
    private Reader reader;

    /**
     * @param writer Объект класса, выводящего в консоль
     * @param listManager Менеджер коллекции
     * @param reader Считыватель полей
     */
    public UpdateCommand(ConsoleWriter writer, ArrayListManager listManager, Reader reader) {
        super("update id {element}", "update the value of the collection item whose id is equal to the given");
        this.writer = writer;
        this.listManager = listManager;
        this.reader = reader;
    }

    @Override
    public CommandCode execute(String argument) {
        try {
            Long arg = Long.parseLong(argument.trim().split(" ")[0]);
            String name = reader.readName();
            Coordinates coordinates = reader.readCoordinates();
            int numberOfParticipants = reader.readNumberOfParticipants();
            Integer singlesCount = reader.readSinglesCount();
            MusicGenre musicGenre = reader.readMusicGenre();
            Label label = reader.readlabel();
            try {
                MusicBand updatingMusicBand = listManager.getById(arg);
                long id = updatingMusicBand.getId();
                LocalDate creationDate = updatingMusicBand.getCreationDate();
                MusicBand musicBand = new MusicBand(id, name, coordinates, creationDate, numberOfParticipants, singlesCount, musicGenre, label);
                listManager.replace(id, musicBand);
            }
            catch (NoSuchIdException e) {
                writer.write(e.getMessage());
            }
        } catch (NumberFormatException e) {
            throw new NoArgumentFoundException();
        }
        return CommandCode.DEFAULT;
    }
}
