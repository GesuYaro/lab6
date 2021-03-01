package Console.commands;

/**
 * Класс команды exit, осуществяющей выход из программы
 */
public class ExitCommand extends AbstractCommand {

    public ExitCommand() {
        super("exit", "finish the command (without saving)");
    }

    /**
     * @param argument
     * @return CommandCode.EXIT
     */
    @Override
    public CommandCode execute(String argument) {
        return CommandCode.EXIT;
    }
}
