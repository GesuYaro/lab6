package Console.commands;


import Console.ConsoleWriter;

import java.util.HashMap;

/**
 * Класс команды help, выводящей справку по командам
 */
public class HelpCommand extends AbstractCommand {
    private HashMap<String, Command> commands;
    private ConsoleWriter writer;

    /**
     * @param writer Объект класса, выводящего в консоль
     * @param commands Мапка с командами
     */
    public HelpCommand(ConsoleWriter writer, HashMap<String, Command> commands) {
        super("help","display help for available commands");
        this.writer = writer;
        this.commands = commands;
    }

    /**
     * Конструктор для того, чтобы положить объект HelpCommand в команду help
     */
    public HelpCommand() {
        super("help","display help for available commands");
    }

    /**
     * @param commands
     */
    public void setCommands(HashMap<String, Command> commands) {
        this.commands = commands;
    }

    /**
     * @param argument
     * @return CommandCode.DEFAULT
     */
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
