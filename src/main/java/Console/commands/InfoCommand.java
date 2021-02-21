package Console.commands;

import CollectionManager.ArrayListManager;
import Console.ConsoleWriter;

import java.io.IOException;
import java.io.Writer;

public class InfoCommand extends AbstractCommand {
    private ConsoleWriter writer;
    private ArrayListManager listManager;
    public InfoCommand(ConsoleWriter writer, ArrayListManager listManager) {
        super("info", "print information about the collection (type, date of initialization, number of elements, etc.) to standard output");
        this.writer = writer;
        this.listManager = listManager;
    }


    @Override
    public CommandCode execute(String argument) {
        writer.write(listManager.info());
        return CommandCode.DEFAULT;
    }
}
