package Console.commands;

import CollectionManager.ArrayListManager;

import java.io.IOException;
import java.io.Writer;

public class InfoCommand extends AbstractCommand {
    private Writer writer;
    private ArrayListManager listManager;
    public InfoCommand(Writer writer, ArrayListManager listManager) {
        super("info", "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
        this.writer = writer;
        this.listManager = listManager;
    }


    @Override
    public CommandCode execute(String argument) {
        try {
            writer.write(listManager.info());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return CommandCode.DEFAULT;
    }
}
