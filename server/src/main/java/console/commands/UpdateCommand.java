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
    public CommandCode execute(String firstArgument, MusicBand requestedMusicBand) throws NoArgumentFoundException, NoSuchIdException{
        try {
            if (firstArgument == null) {
                firstArgument = "";
            }
            Long arg = Long.parseLong(firstArgument.trim().split(" ")[0]);
            MusicBand updatingMusicBand = listManager.getById(arg);
            long id = updatingMusicBand.getId();
            LocalDate creationDate = updatingMusicBand.getCreationDate();
            requestedMusicBand.setCreationDate(creationDate);
            requestedMusicBand.setId(id);
            listManager.replace(id, requestedMusicBand);
        } catch (NumberFormatException e) {
            throw new NoArgumentFoundException();
        }
        return CommandCode.DEFAULT;
    }
}
