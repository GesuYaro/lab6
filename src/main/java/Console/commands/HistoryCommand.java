package Console.commands;

import Console.CommandHandler;
import Console.ConsoleWriter;

import java.io.IOException;
import java.io.Writer;

public class HistoryCommand extends AbstractCommand {
    private ConsoleWriter writer;
    private CommandHandler.HistoryStorage historyStorage;
    public HistoryCommand(ConsoleWriter writer, CommandHandler.HistoryStorage historyStorage) {
        super("history", "вывести последние 7 команд (без их аргументов)");
        this.writer = writer;
        this.historyStorage = historyStorage;
    }

    @Override
    public CommandCode execute(String argument) {
        String[] history = historyStorage.getCommandHistory();
        String historyInLine = "";
        for (String h : history) {
            if (h != null) historyInLine = h + "\n" + historyInLine;
        }
        writer.write(historyInLine);
        return CommandCode.DEFAULT;
    }
}
