package Console.commands;

public class ExitCommand extends AbstractCommand {

    public ExitCommand() {
        super("exit", "завершить программу (без сохранения в файл)");
    }

    @Override
    public CommandCode execute(String argument) {
        return CommandCode.EXIT;
    }
}
