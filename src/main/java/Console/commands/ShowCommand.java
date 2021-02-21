package Console.commands;

import CollectionManager.ArrayListManager;
import Console.ConsoleWriter;

import java.io.IOException;
import java.io.Writer;

public class ShowCommand extends AbstractCommand {

    private ConsoleWriter writer;
    private ArrayListManager listManager;

    public ShowCommand(ConsoleWriter writer, ArrayListManager listManager) {
        super("show", "print all elements of the collection in string representation");
        this.writer = writer;
        this.listManager = listManager;
    }

    @Override
    public CommandCode execute(String argument) {
        writer.write(listManager.show());
        return CommandCode.DEFAULT;
    }
}
