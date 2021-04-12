package console.commands;

import collectionManager.ArrayListManager;
import console.ConsoleWriter;
import console.Writer;


/**
 * Класс команды show, показывающей элементы коллекции
 */
public class ShowCommand extends AbstractCommand {

    private Writer writer;
    private ArrayListManager listManager;

    /**
     * @param writer Объект класса, выводящего в консоль
     * @param listManager Менеджер коллекции
     */
    public ShowCommand(Writer writer, ArrayListManager listManager) {
        super("show", "print all elements of the collection in string representation");
        this.writer = writer;
        this.listManager = listManager;
    }

    /**
     * @param firstArgument
     * @return CommandCode.DEFAULT
     */
    @Override
    public CommandCode execute(String firstArgument, String[] arguments) {
        writer.write(listManager.show());
        return CommandCode.DEFAULT;
    }
}
