import CollectionManager.ArrayListManager;
import Console.*;
import Console.commands.Command;
import Console.commands.*;
import musicband.MusicBand;
import musicband.MusicBandFieldsReader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        ArrayList<MusicBand> musicBandArrayList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        Console console = new Console(); //
        CommandHandler commandHandler = new CommandHandler(); //
        MusicBandFieldsReader musicBandFieldsReader = new MusicBandFieldsReader(reader);
        InteractiveModeReader interactiveModeReader = new InteractiveModeReader(musicBandFieldsReader, console);
        HashMap<String, Command> commands = new HashMap<>();
        ArrayListManager arrayListManager = new ArrayListManager(musicBandArrayList, LocalDate.now());
        commands.put("help", new HelpCommand(console)); //не работает
        commands.put("info", new InfoCommand(console, arrayListManager));
        commands.put("show", new ShowCommand(console, arrayListManager));
        commands.put("add", new AddCommand(console, arrayListManager, interactiveModeReader));
        commands.put("update", new UpdateCommand(console,arrayListManager, interactiveModeReader));
        commands.put("remove_by_id", new RemoveByIdCommand(arrayListManager));
        commands.put("clear", new ClearCommand(arrayListManager));
        commands.put("exit", new ExitCommand());
        commands.put("insert_at", new InsertAtCommand(console, arrayListManager, interactiveModeReader));
        commands.put("remove_last", new RemoveLastCommand(arrayListManager));
        commands.put("history", new HistoryCommand(console, commandHandler)); //не работает
        commandHandler.setCommands(commands);
        console.setCommandHandler(commandHandler);
        console.setReader(reader);
        console.run();
    }
}
