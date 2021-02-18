package Console;

import musicband.*;

import java.time.LocalDate;

public class InteractiveModeReader implements Reader {
    private MusicBandFieldsReader reader;
    private Console console;

    public InteractiveModeReader(MusicBandFieldsReader reader, Console console) {
        this.reader = reader;
        this.console = console;
    }

    public String readName() {
        String name;
        while(true) {
            try {
                console.print("Введите название:");
                name = reader.readName();
                break;
            }
            catch (InputValueExeption e) {
                console.print(e.getMessage());
            }
        }
        return name;
    }

    public Coordinates readCoordinates() {
        Long x;
        Double y;
        while(true) {
            try {
                console.print("Введите координату X:");
                x = reader.readX();
                console.print("Введите координату Y:");
                y = reader.readY();
                break;
            }
            catch (InputValueExeption e) {
                console.print(e.getMessage());
            }
        }
        return new Coordinates(x, y);
    }

    public int readNumberOfParticipants() {
        int numberOfParticipants;
        while(true) {
            try {
                console.print("Введите количество участников:");
                numberOfParticipants = reader.readNumberOfParticipants();
                break;
            }
            catch (InputValueExeption e) {
                console.print(e.getMessage());
            }
        }
        return numberOfParticipants;
    }

    public Integer readSinglesCount() {
        Integer singlesCount;
        while(true) {
            try {
                console.print("Введите количество синглов:");
                singlesCount = reader.readSinglesCount();
                break;
            }
            catch (InputValueExeption e) {
                console.print(e.getMessage());
            }
        }
        return singlesCount;
    }

    public MusicGenre readMusicGenre() {
        MusicGenre musicGenre;
        while(true) {
            try {
                console.print("Выберите жанр из списка:");
                for (MusicGenre genre: MusicGenre.values()) {
                    console.print(genre.name());
                }
                musicGenre = reader.readMusicGenre();
                break;
            }
            catch (InputValueExeption e) {
                console.print(e.getMessage());
            }
        }
        return musicGenre;
    }

    public Label readlabel() {
        Label label;
        while(true) {
            try {
                console.print("Введите название лейбла:");
                label = reader.readLabel();
                break;
            }
            catch (InputValueExeption e) {
                console.print(e.getMessage());
            }
        }
        return label;
    }

}
