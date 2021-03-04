package console.commands;

import collectionManager.ArrayListManager;
import console.ConsoleWriter;


/**
 * Класс команды show, показывающей элементы коллекции
 */
public class ShowCommand extends AbstractCommand {

    private ConsoleWriter writer;
    private ArrayListManager listManager;

    /**
     * @param writer Объект класса, выводящего в консоль
     * @param listManager Менеджер коллекции
     */
    public ShowCommand(ConsoleWriter writer, ArrayListManager listManager) {
        super("show", "print all elements of the collection in string representation");
        this.writer = writer;
        this.listManager = listManager;
    }

    /**
     * @param argument
     * @return CommandCode.DEFAULT
     */
    @Override
    public CommandCode execute(String argument) {
        writer.write(listManager.show());
        return CommandCode.DEFAULT;
    }
}
