package Console.commands;

import CollectionManager.ArrayListManager;
import Console.ConsoleWriter;
import Console.Exeptions.NoArgumentFoundExeption;

import java.util.Iterator;

public class FilterLessThanSinglesCountCommand extends AbstractCommand {
    private ArrayListManager listManager;
    private ConsoleWriter writer;

    public FilterLessThanSinglesCountCommand(ConsoleWriter writer, ArrayListManager listManager) {
        super("filter_less_than_singles_count singles_count", "display elements whose singlesCount field value is less than the specified one");
        this.listManager = listManager;
        this.writer = writer;
    }

    @Override
    public CommandCode execute(String argument) {
        try {
            Integer singlesCount = Integer.parseInt(argument.trim().split(" ")[0].trim());
            String str = "";
            for (Iterator it = listManager.filterLessThanSinglesCount(singlesCount).iterator(); it.hasNext(); ) {
                str += it.next().toString() + "\n";
            }
            writer.write(str);
        } catch (NumberFormatException e) {
            throw new NoArgumentFoundExeption("Argument not found. Enter integer argument");
        }

        return CommandCode.DEFAULT;
    }
}
