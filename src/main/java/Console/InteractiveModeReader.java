package Console;

import musicband.*;

import java.io.IOException;
import java.io.Writer;

public class InteractiveModeReader implements Reader {
    private MusicBandFieldsReader reader;
    private ConsoleWriter writer;

    public InteractiveModeReader(MusicBandFieldsReader reader, ConsoleWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public String readName() {
        String name;
        while(true) {
            try {
                writer.write("Enter the name:");
                name = reader.readName();
                break;
            }
            catch (InputValueExeption e) {
                writer.write(e.getMessage());

            }
        }
        return name;
    }

    public Coordinates readCoordinates() {
        Long x;
        Double y;
        while(true) {
            try {
                writer.write("Enter X coordinate:");
                x = reader.readX();
                writer.write("Enter Y coordinate");
                y = reader.readY();
                break;
            }
        catch (InputValueExeption e) {
            writer.write(e.getMessage());
        }
        }
        return new Coordinates(x, y);
    }

    public int readNumberOfParticipants() {
        int numberOfParticipants;
        while(true) {
            try {
                writer.write("Enter the number of participants:");
                numberOfParticipants = reader.readNumberOfParticipants();
                break;
            } catch (InputValueExeption e) {
                writer.write(e.getMessage());
            }
        }
        return numberOfParticipants;
    }

    public Integer readSinglesCount() {
        Integer singlesCount;
        while(true) {
            try {
                writer.write("Enter singles count:");
                singlesCount = reader.readSinglesCount();
                break;
            } catch (InputValueExeption e) {
                writer.write(e.getMessage());
            }
        }
        return singlesCount;
    }

    public MusicGenre readMusicGenre() {
        MusicGenre musicGenre;
        while(true) {
            try {
                writer.write("Choose the genre from the list:");
                for (MusicGenre genre : MusicGenre.values()) {
                    writer.write(genre.name());
                }
                musicGenre = reader.readMusicGenre();
                break;
            } catch (InputValueExeption e) {
                writer.write(e.getMessage());
            }
        }
        return musicGenre;
    }

    public Label readlabel() {
        Label label;
        while(true) {
            try {
                writer.write("Enter the label name:");
                label = reader.readLabel();
                break;
            } catch (InputValueExeption e) {
                writer.write(e.getMessage());
            }
        }
        return label;
    }

}
