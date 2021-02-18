package Console;

import musicband.Coordinates;
import musicband.Label;
import musicband.MusicGenre;

public interface Reader {
    public String readName();
    public Coordinates readCoordinates();
    public int readNumberOfParticipants();
    public Integer readSinglesCount();
    public MusicGenre readMusicGenre();
    public Label readlabel();

}
