package Console;

import musicband.Coordinates;
import musicband.Label;
import musicband.MusicGenre;

/**
 * Интерфейс для считывателя полей
 */
public interface Reader {
    String readName();
    Coordinates readCoordinates();
    int readNumberOfParticipants();
    Integer readSinglesCount();
    MusicGenre readMusicGenre();
    Label readlabel();

}
