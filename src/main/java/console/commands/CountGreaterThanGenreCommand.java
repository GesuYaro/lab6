package console.commands;

import collectionManager.ArrayListManager;;
import console.ConsoleWriter;
import console.Writer;
import console.exсeptions.NoArgumentFoundException;
import musicband.MusicGenre;

/**
 * Класс для команды count_greater_than_genre, считающей количество элементов, значение поля genre которых больше заданного
 */
public class CountGreaterThanGenreCommand extends AbstractCommand{
    private ArrayListManager listManager;
    private Writer writer;

    /**
     * @param writer Объект класса, осуществляющий вывод в консоль
     * @param listManager Менеджер коллекции
     */
    public CountGreaterThanGenreCommand(Writer writer, ArrayListManager listManager) {
        super("count_greater_than_genre", "print the number of elements whose genre field value is greater than the given one");
        this.listManager = listManager;
        this.writer = writer;
    }

    /**
     * @param firstArgument Значение поля genre
     * @return CommandCode.DEFAULT
     * @throws NoArgumentFoundException
     */
    @Override
    public CommandCode execute(String firstArgument, String[] arguments) throws NoArgumentFoundException {
        try {
            firstArgument = firstArgument.trim().split(" ")[0].toUpperCase();
            MusicGenre genre = MusicGenre.valueOf(firstArgument);
            int count = listManager.countGreaterThanGenre(genre);
            writer.write(String.valueOf(count));
        } catch (IllegalArgumentException e) {
            if (firstArgument.equals("")) {
                throw new NoArgumentFoundException();
            } else {
                throw new NoArgumentFoundException("Genre " + firstArgument + " not found");
            }
        }
        return CommandCode.DEFAULT;
    }
}
