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
                writer.write("Введите название:");
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
                writer.write("Введите координату X:");
                x = reader.readX();
                writer.write("Введите координату Y:");
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
                writer.write("Введите количество участников:");
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
                writer.write("Введите количество синглов:");
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
                writer.write("Выберите жанр из списка:");
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
                writer.write("Введите название лейбла:");
                label = reader.readLabel();
                break;
            } catch (InputValueExeption e) {
                writer.write(e.getMessage());
            }
        }
        return label;
    }

}
