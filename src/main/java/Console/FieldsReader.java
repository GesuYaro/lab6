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


    private Object read(String message, CheckReader checkReader) {
        Object obj;
        while(true) {
            try {
                if (interactiveMode) writer.write(message);
                obj = checkReader.read();
                break;
            } catch (InputValueException e) {
                writer.write(e.getMessage());
            }
        }
        return obj;
    }

    /**
     * @return Корректное название, введенное пользователем
     */
    public String readName() {
        return (String) read("Enter the name:", checker::readName);
    }


    /**
     * @return Корректные координаты, введенные пользователем
     */
    public Coordinates readCoordinates() {
        Long x;
        Double y;
        x = (Long) read("Enter X coordinate:", checker::readX);
        y = (Double) read("Enter Y coordinate:", checker::readY);
        return new Coordinates(x, y);
    }

    /**
     * @return Корректное количество участников, введенное пользователем
     */
    public int readNumberOfParticipants() {
        return (int) read("Enter the number of participants:", checker::readNumberOfParticipants);
    }

    /**
     * @return Корректное количество синглов, введенное пользователем
     */
    public Integer readSinglesCount() {
        return (Integer) read("Enter the singles count:", checker::readSinglesCount);
    }

    /**
     * @return Корректный жанр, введенный пользователем
     */
    public MusicGenre readMusicGenre() {
        String message = "Choose the genre from the list:";
        for (MusicGenre genre : MusicGenre.values()) {
            message += "\n" + genre.name();
        }
        MusicGenre musicGenre = (MusicGenre) this.read(message, checker::readMusicGenre);
        return musicGenre;
    }

//    public MusicGenre readMusicGenre() {
//        MusicGenre musicGenre;
//        while(true) {
//            try {
//                if (interactiveMode) {
//                    writer.write("Choose the genre from the list:");
//                    for (MusicGenre genre : MusicGenre.values()) {
//                        writer.write(genre.name());
//                    }
//                }
//                musicGenre = checker.readMusicGenre();
//                break;
//            } catch (InputValueException e) {
//                writer.write(e.getMessage());
//            }
//        }
//        return musicGenre;
//    }

    /**
     * @return Корректный лейбл, введенный пользователем
     */
    public Label readlabel() {
        return (Label) read("Enter the label name:", checker::readLabel);
    }

}
