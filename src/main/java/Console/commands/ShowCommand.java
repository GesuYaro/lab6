package Console.commands;

import CollectionManager.ArrayListManager;
import Console.Console;

public class ShowCommand extends AbstractCommand {

    private Console console;
    private ArrayListManager listManager;

    public ShowCommand(Console console, ArrayListManager listManager) {
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        this.console = console;
        this.listManager = listManager;
    }

    @Override
    public CommandCode execute(String argument) {
        console.print(listManager.show());
        return CommandCode.DEFAULT;
    }
}
