package console.commands;

import collectionManager.ArrayListManager;;
import console.ConsoleWriter;
import console.exсeptions.NoArgumentFoundException;
import musicband.MusicGenre;

/**
 * Класс для команды count_greater_than_genre, считающей количество элементов, значение поля genre которых больше заданного
 */
public class CountGreaterThanGenreCommand extends AbstractCommand{
    private ArrayListManager listManager;
    private ConsoleWriter writer;

    /**
     * @param writer Объект класса, осуществляющий вывод в консоль
     * @param listManager Менеджер коллекции
     */
    public CountGreaterThanGenreCommand(ConsoleWriter writer, ArrayListManager listManager) {
        super("count_greater_than_genre", "print the number of elements whose genre field value is greater than the given one");
        this.listManager = listManager;
        this.writer = writer;
    }

    /**
     * @param argument Значение поля genre
     * @return CommandCode.DEFAULT
     * @throws NoArgumentFoundException
     */
    @Override
    public CommandCode execute(String argument) {
        try {
            argument = argument.trim().split(" ")[0].toUpperCase();
            MusicGenre genre = MusicGenre.valueOf(argument);
            int count = listManager.countGreaterThanGenre(genre);
            writer.write(String.valueOf(count));
        } catch (IllegalArgumentException e) {
            if (argument.equals("")) {
                throw new NoArgumentFoundException();
            } else {
                throw new NoArgumentFoundException("Genre " + argument + " not found");
            }
        }
        return CommandCode.DEFAULT;
    }
}
