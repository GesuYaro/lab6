package Console.commands;


import Console.ConsoleWriter;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;

public class HelpCommand extends AbstractCommand {
    private HashMap<String, Command> commands;
    private ConsoleWriter writer;
    public HelpCommand(ConsoleWriter writer, HashMap<String, Command> commands) {
        super("help","вывести справку по доступным командам");
        this.commands = commands;
    }
    public HelpCommand() {
        super("help","вывести справку по доступным командам");
    }

    public void setCommands(HashMap<String, Command> commands) {
        this.commands = commands;
    }

    @Override
    public CommandCode execute(String argument) {
        String description = "";
        for (String key : commands.keySet()) {
            Command command;
            command = commands.get(key);
            description += command.getName() + " : " + command.getDescription() + "\n";
        }
        writer.write(description);

        return CommandCode.DEFAULT;
    }
}
