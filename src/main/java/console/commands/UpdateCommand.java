package console.commands;

import collectionManager.ArrayListManager;
import console.exсeptions.NoArgumentFoundException;
import console.exсeptions.NoSuchIdException;
import musicband.*;

import java.time.LocalDate;

public class UpdateCommand extends AbstractCommand {

    private ArrayListManager listManager;
    private MusicBandFieldsChecker fieldsChecker;

    /**
     * @param listManager Менеджер коллекции
     * @param fieldsChecker Считыватель полей
     */
    public UpdateCommand(ArrayListManager listManager, MusicBandFieldsChecker fieldsChecker) {
        super("update id {element}", "update the value of the collection item whose id is equal to the given");
        this.listManager = listManager;
        this.fieldsChecker = fieldsChecker;
    }

    @Override
    public CommandCode execute(String firstArgument, String[] arguments) throws NoArgumentFoundException, NoSuchIdException{
        try {
            if (firstArgument == null) {
                firstArgument = "";
            }
            Long arg = Long.parseLong(firstArgument.trim().split(" ")[0]);
            String name = fieldsChecker.readName(arguments[0]);
            Coordinates coordinates = new Coordinates(fieldsChecker.readX(arguments[1]), fieldsChecker.readY(arguments[2]));
            int numberOfParticipants = fieldsChecker.readNumberOfParticipants(arguments[3]);
            Integer singlesCount = fieldsChecker.readSinglesCount(arguments[4]);
            MusicGenre musicGenre = fieldsChecker.readMusicGenre(arguments[5]);
            Label label = fieldsChecker.readLabel(arguments[6]);
            MusicBand updatingMusicBand = listManager.getById(arg);
            long id = updatingMusicBand.getId();
            LocalDate creationDate = updatingMusicBand.getCreationDate();
            MusicBand musicBand = new MusicBand(id, name, coordinates, creationDate, numberOfParticipants, singlesCount, musicGenre, label);
            listManager.replace(id, musicBand);
        } catch (NumberFormatException e) {
            throw new NoArgumentFoundException();
        }
        return CommandCode.DEFAULT;
    }
}
