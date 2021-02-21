package Console.commands;

import CollectionManager.ArrayListManager;
import Console.ConsoleWriter;
import musicband.MusicBand;

import java.util.Iterator;

public class PrintFieldsDescendingGenreCommand extends AbstractCommand {
    private ConsoleWriter writer;
    private ArrayListManager listManager;

    public PrintFieldsDescendingGenreCommand(ConsoleWriter writer, ArrayListManager listManager) {
        super("print_field_descending_genre", "display the genre field values of all elements in descending order");
        this.writer = writer;
        this.listManager = listManager;
    }

    @Override
    public CommandCode execute(String argument) {
        String str = "";
        if (listManager.getArrayList().size() < 1) {
            str = "Collection is empty";
        } else {
            for (Iterator<MusicBand> it = listManager.sortByGenre().iterator(); it.hasNext(); ) {
                str += it.next().getGenre().name();
            }
        }
        writer.write(str);
        return CommandCode.DEFAULT;
    }
}
