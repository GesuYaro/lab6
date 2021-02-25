package Console;

import musicband.*;

public class ScriptModeReader implements Reader {

    private MusicBandFieldsReader reader;
    private ConsoleWriter writer;

    @Override
    public String readName() {
        String name;
        while(true) {
            try {
                name = reader.readName();
                break;
            }
            catch (InputValueExeption e) {
                writer.write("Error in the script" + e.getMessage());
            }
        }
        return name;
    }

    @Override
    public Coordinates readCoordinates() {
        return null;
    }

    @Override
    public int readNumberOfParticipants() {
        return 0;
    }

    @Override
    public Integer readSinglesCount() {
        return null;
    }

    @Override
    public MusicGenre readMusicGenre() {
        return null;
    }

    @Override
    public Label readlabel() {
        return null;
    }
}
