import CollectionManager.ArrayListManager;
import Console.*;
import Console.Console;
import Console.commands.Command;
import Console.commands.*;
import musicband.MusicBand;
import musicband.MusicBandFieldsReader;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        ArrayList<MusicBand> musicBandArrayList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        //OutputStreamWriter writer = new OutputStreamWriter(System.out);
        ConsoleWriter writer = new ConsoleWriter(); ///////////////////////////////////////
        CommandHandler.HistoryStorage historyStorage = new CommandHandler.HistoryStorage();
        MusicBandFieldsReader musicBandFieldsReader = new MusicBandFieldsReader(reader);
        InteractiveModeReader interactiveModeReader = new InteractiveModeReader(musicBandFieldsReader, writer);
        HashMap<String, Command> commands = new HashMap<>();
        ArrayListManager arrayListManager = new ArrayListManager(musicBandArrayList, LocalDate.now());
        commands.put("help", new HelpCommand());
        commands.put("info", new InfoCommand(writer, arrayListManager));
        commands.put("show", new ShowCommand(writer, arrayListManager));
        commands.put("add", new AddCommand(arrayListManager, interactiveModeReader));
        commands.put("update", new UpdateCommand(writer,arrayListManager, interactiveModeReader)); //проверка на наличие аргумента
        commands.put("remove_by_id", new RemoveByIdCommand(arrayListManager));
        commands.put("clear", new ClearCommand(arrayListManager));
        commands.put("exit", new ExitCommand());
        commands.put("insert_at", new InsertAtCommand(writer, arrayListManager, interactiveModeReader));
        commands.put("remove_last", new RemoveLastCommand(arrayListManager));
        commands.put("history", new HistoryCommand(writer,historyStorage)); // должно начать работать
        CommandHandler commandHandler = new CommandHandler(commands, historyStorage);
        commands.put("help", new HelpCommand(writer, commands));
        Console console = new Console(commandHandler, reader, writer);
        console.run();
    }
}
