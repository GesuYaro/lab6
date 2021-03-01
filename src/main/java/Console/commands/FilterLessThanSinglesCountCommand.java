package Console.commands;

import CollectionManager.ArrayListManager;
import Console.ConsoleWriter;
import Console.Exсeptions.NoArgumentFoundException;

import java.util.Iterator;

/**
 * Класс команды filter_less_than_singles_count, выводящей элементы, поле singlesCount которых меньше, чем задано
 */
public class FilterLessThanSinglesCountCommand extends AbstractCommand {
    private ArrayListManager listManager;
    private ConsoleWriter writer;

    /**
     * @param writer Объект класса, осуществляющего вывод в консоль
     * @param listManager Менеджер коллекции
     */
    public FilterLessThanSinglesCountCommand(ConsoleWriter writer, ArrayListManager listManager) {
        super("filter_less_than_singles_count singles_count", "display elements whose singlesCount field value is less than the specified one");
        this.listManager = listManager;
        this.writer = writer;
    }

    /**
     * @param argument singles_count
     * @return CommandCode.DEFAULT
     * @throws NoArgumentFoundException
     */
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
            throw new NoArgumentFoundException("Argument not found. Enter integer argument");
        }

        return CommandCode.DEFAULT;
    }
}
