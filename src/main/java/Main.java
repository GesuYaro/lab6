import CollectionManager.ArrayListInitializer;
import CollectionManager.ArrayListManager;
import CollectionManager.Parser;
import Console.*;
import Console.Console;
import Console.Exсeptions.ReadOrWriteFileException;
import Console.commands.Command;
import Console.commands.*;
import musicband.MusicBand;
import musicband.MusicBandFieldsChecker;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Главный класс, в котором создаются объекты и запускается приложение
 */
public class Main {
    public static void main(String[] args) {
        ConsoleWriter writer = new ConsoleWriter();
        ArrayList<MusicBand> musicBandArrayList = new ArrayList<>();
        LocalDate initializationDate = LocalDate.now();
        File file = null;
        String path;
        //System.out.println(path);
        try {
            path = System.getenv("LAB5_PATH").replace("\u202A", "");
            file = new File(path);
            if (!file.exists()) file.createNewFile();
            if (!file.canRead() || !file.canWrite()) throw new ReadOrWriteFileException();
            BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            ArrayListInitializer arrayListInitializer = new ArrayListInitializer(fileReader, new MusicBandFieldsChecker(), new Parser());
            musicBandArrayList = arrayListInitializer.init();
            if (arrayListInitializer.getInitializationDate() != null)  initializationDate = arrayListInitializer.getInitializationDate();
        } catch (NullPointerException e) {
            writer.write("Error with environment variable");
        } catch (SecurityException e) {
            writer.write("File not found. Access denied. Can't create a new file.");
        } catch (ReadOrWriteFileException e) {
            writer.write("Access denied. Can't read or write file");
        } catch (FileNotFoundException e) {
            writer.write("File not found");
        } catch (IOException e) {
            writer.write("Unexpected error with initialization");
        }


        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        CommandHandler.HistoryStorage historyStorage = new CommandHandler.HistoryStorage();
        MusicBandFieldsChecker musicBandFieldsChecker = new MusicBandFieldsChecker(reader);
        FieldsReader fieldsReader = new FieldsReader(musicBandFieldsChecker, writer);
        HashMap<String, Command> commands = new HashMap<>();
        ArrayListManager arrayListManager = new ArrayListManager(musicBandArrayList, initializationDate);
        commands.put("help", new HelpCommand());
        commands.put("info", new InfoCommand(writer, arrayListManager));
        commands.put("show", new ShowCommand(writer, arrayListManager));
        commands.put("add", new AddCommand(arrayListManager, fieldsReader));
        commands.put("update", new UpdateCommand(writer,arrayListManager, fieldsReader));
        commands.put("remove_by_id", new RemoveByIdCommand(arrayListManager));
        commands.put("clear", new ClearCommand(arrayListManager));
        commands.put("save", new SaveCommand(arrayListManager, file, writer));
        commands.put("exit", new ExitCommand());
        commands.put("insert_at", new InsertAtCommand(writer, arrayListManager, fieldsReader));
        commands.put("remove_last", new RemoveLastCommand(arrayListManager));
        commands.put("history", new HistoryCommand(writer,historyStorage));
        commands.put("count_greater_than_genre", new CountGreaterThanGenreCommand(writer, arrayListManager));
        commands.put("filter_less_than_singles_count", new FilterLessThanSinglesCountCommand(writer, arrayListManager));
        commands.put("print_field_descending_genre", new PrintFieldsDescendingGenreCommand(writer, arrayListManager));
        commands.put("execute_script", new ExecuteScriptCommand(writer, arrayListManager, historyStorage));
        commands.put("help", new HelpCommand(writer, commands));
        CommandHandler commandHandler = new CommandHandler(commands, historyStorage);
        Console console = new Console(commandHandler, reader, writer);
        console.run();
    }
}
