package Console.commands;

import CollectionManager.ArrayListManager;
import Console.ConsoleWriter;
import Console.Exeptions.NoSuchIdExeption;
import Console.Reader;
import musicband.Coordinates;
import musicband.Label;
import musicband.MusicBand;
import musicband.MusicGenre;

import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;

public class UpdateCommand extends AbstractCommand {

    private ConsoleWriter writer;
    private ArrayListManager listManager;
    private Reader reader;

    public UpdateCommand(ConsoleWriter writer, ArrayListManager listManager, Reader reader) {
        super("update id {element}", "обновить значение элемента коллекции, id которого равен заданному");
        this.writer = writer;
        this.listManager = listManager;
        this.reader = reader;
    }

    @Override
    public CommandCode execute(String argument) {
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
        catch (NoSuchIdExeption e) {
            writer.write(e.getMessage());
        }
        return CommandCode.DEFAULT;
    }
}
