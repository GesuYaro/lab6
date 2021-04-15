package console.commands;

import collectionManager.ArrayListManager;
import server.ServerWriter;


/**
 * Класс команды show, показывающей элементы коллекции
 */
public class ShowCommand extends AbstractCommand {

    private ServerWriter writer;
    private ArrayListManager listManager;

    /**
     * @param writer Объект класса, выводящего в консоль
     * @param listManager Менеджер коллекции
     */
    public ShowCommand(ServerWriter writer, ArrayListManager listManager) {
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
        writer.write(listManager.getArrayList());
        return CommandCode.DEFAULT;
    }
}
