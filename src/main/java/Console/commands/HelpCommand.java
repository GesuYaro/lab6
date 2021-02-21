package Console.commands;


import Console.ConsoleWriter;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;

public class HelpCommand extends AbstractCommand {
    private HashMap<String, Command> commands;
    private ConsoleWriter writer;
    public HelpCommand(ConsoleWriter writer, HashMap<String, Command> commands) {
        super("help","display help for available commands");
        this.writer = writer;
        this.commands = commands;
    }
    public HelpCommand() {
        super("help","display help for available commands");
    }

    public void setCommands(HashMap<String, Command> commands) {
        this.commands = commands;
    }

    @Override
    public CommandCode execute(String argument) {
        String description = "Commands:\n";
        for (String key : commands.keySet()) {
            Command command;
            command = commands.get(key);
            description += command.getName() + " : " + command.getDescription() + "\n";
        }
        //System.out.println(description);
        writer.write(description);

        return CommandCode.DEFAULT;
    }
}
