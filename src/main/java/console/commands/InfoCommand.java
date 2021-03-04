package console.commands;

import collectionManager.ArrayListManager;
import console.ConsoleWriter;

/**
 * Класс команды info, выводящей информацию о коллекции
 */
public class InfoCommand extends AbstractCommand {
    private ConsoleWriter writer;
    private ArrayListManager listManager;

    /**
     * @param writer Объект класса, выводящего в консоль
     * @param listManager Менеджер коллекции
     */
    public InfoCommand(ConsoleWriter writer, ArrayListManager listManager) {
        super("info", "print information about the collection (type, date of initialization, number of elements, etc.) to standard output");
        this.writer = writer;
        this.listManager = listManager;
    }


    /**
     * @param argument
     * @return CommandCode.DEFAULT
     */
    @Override
    public CommandCode execute(String argument) {
        writer.write(listManager.info());
        return CommandCode.DEFAULT;
    }
}
