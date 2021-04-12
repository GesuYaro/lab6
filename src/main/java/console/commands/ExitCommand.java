package console.commands;

/**
 * Класс команды exit, осуществяющей выход из программы
 */
public class ExitCommand extends AbstractCommand {

    public ExitCommand() {
        super("exit", "finish the command (without saving)");
    }

    /**
     * @param firstArgument
     * @param arguments
     * @return CommandCode.EXIT
     */
    @Override
    public CommandCode execute(String firstArgument, String[] arguments) {
        return CommandCode.EXIT;
    }
}
