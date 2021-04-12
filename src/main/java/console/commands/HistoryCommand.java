package console.commands;

import console.CommandHandler;
import console.ConsoleWriter;
import console.Writer;


/**
 * Класс команды history, выводящей историю команд
 */
public class HistoryCommand extends AbstractCommand {
    private Writer writer;
    private CommandHandler.HistoryStorage historyStorage;

    /**
     * @param writer Объект класса, выводящего в консоль
     * @param historyStorage Объект класса, хранящего историю команд
     */
    public HistoryCommand(Writer writer, CommandHandler.HistoryStorage historyStorage) {
        super("history", "print the last 7 commands (without their arguments)");
        this.writer = writer;
        this.historyStorage = historyStorage;
    }

    /**
     * @param firstArgument
     * @param arguments
     * @return CommandCode.DEFAULT
     */
    @Override
    public CommandCode execute(String firstArgument, String[] arguments) {
        String[] history = historyStorage.getCommandHistory();
        String historyInLine = "";
        for (String h : history) {
            if (h != null) historyInLine = h + "\n" + historyInLine;
        }
        writer.write(historyInLine);
        return CommandCode.DEFAULT;
    }
}
