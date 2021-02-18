package Console.commands;

import Console.CommandHandler;

import java.io.IOException;
import java.io.Writer;

public class HistoryCommand extends AbstractCommand {
    private Writer writer;
    private CommandHandler.HistoryStorage historyStorage;
    public HistoryCommand(Writer writer, CommandHandler.HistoryStorage historyStorage) {
        super("history", "вывести последние 7 команд (без их аргументов)");
        this.writer = writer;
        this.historyStorage = historyStorage;
    }

    @Override
    public CommandCode execute(String argument) {
        String[] history = historyStorage.getCommandHistory();
        String historyInLine = "";
        for (String h : history) {
            if (h != null) historyInLine += h + "\n";
        }
        try {
            writer.write(historyInLine);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return CommandCode.DEFAULT;
    }
}
