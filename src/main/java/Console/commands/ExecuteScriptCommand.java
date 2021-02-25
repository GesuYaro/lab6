package Console.commands;

import CollectionManager.ArrayListManager;
import Console.*;
import Console.Console;
import Console.Exeptions.NoArgumentFoundExeption;
import Console.Exeptions.ReadOrWriteFileExeption;
import musicband.MusicBandFieldsReader;
import Console.CommandHandler.HistoryStorage;

import java.io.*;
import java.util.HashMap;


public class ExecuteScriptCommand extends AbstractCommand {

    private ConsoleWriter writer;
    //private CommandHandler commandHandler;
    private ArrayListManager arrayListManager;
    private HistoryStorage historyStorage;

    public ExecuteScriptCommand(ConsoleWriter writer, ArrayListManager listManager, HistoryStorage historyStorage){
        super("execute_script file_name", "Execute script from the file");
        this.writer = writer;
        //this.commandHandler = commandHandler;
        this.arrayListManager = listManager;
        this.historyStorage = historyStorage;
    }

    public ExecuteScriptCommand() {
        super("execute_script file_name", "Execute script from the file");
    }


    @Override
    public CommandCode execute(String argument) {
        String path = argument.trim();
        if (path.equals("")) throw new NoArgumentFoundExeption();
        File file = new File(path);
        try {
            if (!file.exists()) throw new FileNotFoundException();
            if (!file.canRead()) throw new ReadOrWriteFileExeption();
            BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            MusicBandFieldsReader fieldsReader = new MusicBandFieldsReader(fileReader);
            InteractiveModeReader scriptModeReader = new InteractiveModeReader(fieldsReader, writer, false);
            HashMap<String, Command> commands = new HashMap<>();
            commands.put("help", new HelpCommand());
            commands.put("info", new InfoCommand(writer, arrayListManager));
            commands.put("show", new ShowCommand(writer, arrayListManager));
            commands.put("add", new AddCommand(arrayListManager, scriptModeReader));
            commands.put("update", new UpdateCommand(writer,arrayListManager, scriptModeReader));
            commands.put("remove_by_id", new RemoveByIdCommand(arrayListManager));
            commands.put("clear", new ClearCommand(arrayListManager));
            commands.put("save", new SaveCommand(arrayListManager,file));
            commands.put("exit", new ExitCommand());
            commands.put("insert_at", new InsertAtCommand(writer, arrayListManager, scriptModeReader));
            commands.put("remove_last", new RemoveLastCommand(arrayListManager));
            commands.put("history", new HistoryCommand(writer,historyStorage));
            commands.put("count_greater_than_genre", new CountGreaterThanGenreCommand(writer, arrayListManager));
            commands.put("filter_less_than_singles_count", new FilterLessThanSinglesCountCommand(writer, arrayListManager));
            commands.put("print_field_descending_genre", new PrintFieldsDescendingGenreCommand(writer, arrayListManager));
            commands.put("execute_script", new ExecuteScriptCommand());
            commands.put("help", new HelpCommand(writer, commands));
            commands.put("execute_script", new ExecuteScriptCommand(writer, arrayListManager, historyStorage));
            CommandHandler commandHandler = new CommandHandler(commands, historyStorage);
            Console console = new Console(commandHandler, fileReader, writer);
            try {
                console.run();
            } catch (NullPointerException | StackOverflowError e) {
                writer.write("Error. Incorrect Script");
            }
        } catch (FileNotFoundException | SecurityException e) {
            writer.write("Can't access the file " + path);
        } catch (ReadOrWriteFileExeption e) {
            writer.write("Access denied. Can't read the file");
        }
        return CommandCode.DEFAULT;
    }
}
