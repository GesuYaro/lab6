package console.commands;

import musicband.MusicBand;

/**
 * Интерфейс для команд
 */
public interface Command {
    CommandCode execute(String firstArgument, MusicBand requestedMusicBand);
    String getName();
    String getDescription();
}
