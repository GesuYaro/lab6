package Console.commands;

import CollectionManager.ArrayListManager;;
import Console.ConsoleWriter;
import Console.Exeptions.NoArgumentFoundExeption;
import musicband.MusicGenre;

public class CountGreaterThanGenreCommand extends AbstractCommand{
    private ArrayListManager listManager;
    private ConsoleWriter writer;

    public CountGreaterThanGenreCommand(ConsoleWriter writer, ArrayListManager listManager) {
        super("count_greater_than_genre", "print the number of elements whose genre field value is greater than the given one");
        this.listManager = listManager;
        this.writer = writer;
    }

    @Override
    public CommandCode execute(String argument) {
        try {
            argument = argument.trim().split(" ")[0].toUpperCase();
            MusicGenre genre = MusicGenre.valueOf(argument);
            int count = listManager.countGreaterThanGenre(genre);
            writer.write(String.valueOf(count));
        } catch (IllegalArgumentException e) {
            if (argument.equals("")) {
                throw new NoArgumentFoundExeption();
            } else {
                throw new NoArgumentFoundExeption("Genre " + argument + " not found");
            }
        }
        return CommandCode.DEFAULT;
    }
}
