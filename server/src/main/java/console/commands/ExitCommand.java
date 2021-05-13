package console.commands;

import musicband.MusicBand;

/**
 * Класс команды exit, осуществяющей выход из программы
 */
public class ExitCommand extends AbstractCommand {

    public ExitCommand() {
        super("exit", "finish the command (without saving)");
    }

    /**
     * @param firstArgument
     * @param requestedMusicBand
     * @return CommandCode.EXIT
     */
    @Override
    public CommandCode execute(String firstArgument, MusicBand requestedMusicBand) {
        return CommandCode.EXIT;
    }
}
