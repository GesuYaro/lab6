package Console.commands;

import CollectionManager.ArrayListManager;
import Console.ConsoleWriter;

import java.io.IOException;
import java.io.Writer;

public class InfoCommand extends AbstractCommand {
    private ConsoleWriter writer;
    private ArrayListManager listManager;
    public InfoCommand(ConsoleWriter writer, ArrayListManager listManager) {
        super("info", "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
        this.writer = writer;
        this.listManager = listManager;
    }


    @Override
    public CommandCode execute(String argument) {
        writer.write(listManager.info());
        return CommandCode.DEFAULT;
    }
}
