package Console.commands;

public class ExitCommand extends AbstractCommand {

    public ExitCommand() {
        super("exit", "finish the command (without saving)");
    }

    @Override
    public CommandCode execute(String argument) {
        return CommandCode.EXIT;
    }
}
