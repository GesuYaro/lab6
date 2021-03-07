package console.commands;

import collectionManager.ArrayListManager;
import console.*;
import console.Console;
import console.exсeptions.IncorrectScriptException;
import console.exсeptions.NoArgumentFoundException;
import musicband.MusicBandFieldsChecker;
import console.CommandHandler.HistoryStorage;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;


/**
 * Класс команды execute_script, исполняющей скрипт
 */
public class ExecuteScriptCommand extends AbstractCommand {

    private ConsoleWriter writer;
    private ArrayListManager arrayListManager;
    private HistoryStorage historyStorage;
    private HashSet<File> scripts;

    /**
     * @param writer Объект класса, осуществляющего вывод в консоль
     * @param listManager Менеджер коллекции
     * @param historyStorage Объект класса, хранящего историю команд
     */
    public ExecuteScriptCommand(ConsoleWriter writer, ArrayListManager listManager, HistoryStorage historyStorage){
        super("execute_script file_name", "Execute script from the file");
        this.writer = writer;
        this.arrayListManager = listManager;
        this.historyStorage = historyStorage;
        this.scripts = new HashSet<>();
    }

    public ExecuteScriptCommand(ConsoleWriter writer, ArrayListManager listManager, HistoryStorage historyStorage, HashSet<File> scripts){
        super("execute_script file_name", "Execute script from the file");
        this.writer = writer;
        this.arrayListManager = listManager;
        this.historyStorage = historyStorage;
        this.scripts = scripts;
    }

    /**
     * Конструктор для добавления команды в help
     */
    public ExecuteScriptCommand() {
        super("execute_script file_name", "Execute script from the file");
    }


    /**
     * @param argument Название файла скрипта
     * @return CommandCode.DEFAULT
     * @throws NoArgumentFoundException
     */
    @Override
    public CommandCode execute(String argument) {
        String path = argument.trim();
        if (path.equals("")) throw new NoArgumentFoundException();
        File file = new File(path);
        try {
            if (!file.exists()) throw new FileNotFoundException();
            if (file.canRead()) {
                if (!scripts.contains(file)) {
                    scripts.add(file);
                    BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                    MusicBandFieldsChecker fieldsReader = new MusicBandFieldsChecker(fileReader);
                    FieldsReader scriptModeReader = new FieldsReader(fieldsReader, writer, false);
                    HashMap<String, Command> commands = new HashMap<>();
                    commands.put("help", new HelpCommand());
                    commands.put("info", new InfoCommand(writer, arrayListManager));
                    commands.put("show", new ShowCommand(writer, arrayListManager));
                    commands.put("add", new AddCommand(arrayListManager, scriptModeReader));
                    commands.put("update", new UpdateCommand(writer, arrayListManager, scriptModeReader));
                    commands.put("remove_by_id", new RemoveByIdCommand(arrayListManager));
                    commands.put("clear", new ClearCommand(arrayListManager));
                    commands.put("save", new SaveCommand(arrayListManager, file, writer));
                    commands.put("exit", new ExitCommand());
                    commands.put("insert_at", new InsertAtCommand(writer, arrayListManager, scriptModeReader));
                    commands.put("remove_last", new RemoveLastCommand(arrayListManager));
                    commands.put("history", new HistoryCommand(writer, historyStorage));
                    commands.put("count_greater_than_genre", new CountGreaterThanGenreCommand(writer, arrayListManager));
                    commands.put("filter_less_than_singles_count", new FilterLessThanSinglesCountCommand(writer, arrayListManager));
                    commands.put("print_field_descending_genre", new PrintFieldsDescendingGenreCommand(writer, arrayListManager));
                    commands.put("execute_script", new ExecuteScriptCommand());
                    commands.put("help", new HelpCommand(writer, commands));
                    commands.put("execute_script", new ExecuteScriptCommand(writer, arrayListManager, historyStorage, scripts));
                    CommandHandler commandHandler = new CommandHandler(commands, historyStorage);
                    Console console = new Console(commandHandler, fileReader, writer);
                    try {
                        console.run();
                    } catch (IncorrectScriptException e) {
                        writer.write(e.getMessage());
                    } catch (NullPointerException | StackOverflowError e) {
                        writer.write("Error. Incorrect Script");
                    }
                } else {
                    writer.write("Recursion found. Stopping the script");
                }
            } else {
                writer.write("Access denied. Can't read the file");
            }
        } catch (FileNotFoundException | SecurityException e) {
            writer.write("Can't access the file " + path);
        }
        scripts.clear();
        return CommandCode.DEFAULT;
    }
}
