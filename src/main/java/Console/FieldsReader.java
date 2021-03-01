package Console;

import Console.Exсeptions.InputValueException;
import musicband.*;


/**
 * Считываетель полей для создания объектов
 * Для каждого поля запускает цикл, ожидающий корректного значения
 */
public class FieldsReader implements Reader {
    private MusicBandFieldsChecker checker;
    private ConsoleWriter writer;
    private boolean interactiveMode;

    /**
     * Создает интерактивный считыватель полей
     * @param checker Проверяльщик полей на корректность
     * @param writer Выводяльщик в консоль
     */
    public FieldsReader(MusicBandFieldsChecker checker, ConsoleWriter writer) {
        this.checker = checker;
        this.writer = writer;
        this.interactiveMode = true;
    }

    /**
     * Создает считыватель полей
     * @param checker Проверяльщик полей на корректность
     * @param writer Выводяльщик в консоль
     * @param interactiveMode Если false, то считыватель не будет просить вводить поля (нужно для скрипт-мода)
     */
    public FieldsReader(MusicBandFieldsChecker checker, ConsoleWriter writer, boolean interactiveMode) {
        this.checker = checker;
        this.writer = writer;
        this.interactiveMode = interactiveMode;
    }

    /**
     * @return Корректное название, введенное пользователем
     */
    public String readName() {
        String name;
        while(true) {
            try {
                if (interactiveMode) writer.write("Enter the name:");
                name = checker.readName();
                break;
            }
            catch (InputValueException e) {
                writer.write(e.getMessage());

            }
        }
        return name;
    }

    /**
     * @return Корректные координаты, введенные пользователем
     */
    public Coordinates readCoordinates() {
        Long x;
        Double y;
        while (true) {
            try {
                if (interactiveMode) writer.write("Enter X coordinate:");
                x = checker.readX();
                break;
            } catch (InputValueException e) {
                writer.write(e.getMessage());
            }
        }
        while (true) {
            try {
                if (interactiveMode) writer.write("Enter Y coordinate");
                y = checker.readY();
                break;
            } catch (InputValueException e) {
                writer.write(e.getMessage());
            }
        }
        return new Coordinates(x, y);
    }

    /**
     * @return Корректное количество участников, введенное пользователем
     */
    public int readNumberOfParticipants() {
        int numberOfParticipants;
        while(true) {
            try {
                if (interactiveMode) writer.write("Enter the number of participants:");
                numberOfParticipants = checker.readNumberOfParticipants();
                break;
            } catch (InputValueException e) {
                writer.write(e.getMessage());
            }
        }
        return numberOfParticipants;
    }

    /**
     * @return Корректное количество синглов, введенное пользователем
     */
    public Integer readSinglesCount() {
        Integer singlesCount;
        while(true) {
            try {
                if (interactiveMode) writer.write("Enter singles count:");
                singlesCount = checker.readSinglesCount();
                break;
            } catch (InputValueException e) {
                writer.write(e.getMessage());
            }
        }
        return singlesCount;
    }

    /**
     * @return Корректный жанр, введенный пользователем
     */
    public MusicGenre readMusicGenre() {
        MusicGenre musicGenre;
        while(true) {
            try {
                if (interactiveMode) {
                    writer.write("Choose the genre from the list:");
                    for (MusicGenre genre : MusicGenre.values()) {
                        writer.write(genre.name());
                    }
                }
                musicGenre = checker.readMusicGenre();
                break;
            } catch (InputValueException e) {
                writer.write(e.getMessage());
            }
        }
        return musicGenre;
    }

    /**
     * @return Корректный лейбл, введенный пользователем
     */
    public Label readlabel() {
        Label label;
        while(true) {
            try {
                if (interactiveMode) writer.write("Enter the label name:");
                label = checker.readLabel();
                break;
            } catch (InputValueException e) {
                writer.write(e.getMessage());
            }
        }
        return label;
    }

}
