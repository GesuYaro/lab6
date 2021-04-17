package server;

import collectionManager.ArrayListManager;
import console.CommandHandler;
import console.Writer;
import console.commands.*;
import musicband.MusicBandFieldsChecker;
import org.slf4j.Logger;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.HashMap;

public class Console {

    private ArrayListManager arrayListManager;
    private File savingFile;
    private Connector connector;
    private Logger logger;

    public Console(ArrayListManager arrayListManager, File savingFile, Connector connector, Logger logger) {
        this.arrayListManager = arrayListManager;
        this.savingFile = savingFile;
        this.connector = connector;
        this.logger = logger;
    }

    public void run(boolean singleIterationMode) {
        Command saveCommand = new SaveCommand(arrayListManager, savingFile, logger);
        do {
            SocketChannel socketChannel = null;
            try {
                while (socketChannel == null) {
                    socketChannel = connector.getSocketChannel();
                }
                logger.info("Connected");
            } catch (IOException e) {
                logger.warn("Can not connect");
            }
            ServerWriter writer = new ServerWriter(socketChannel);


            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            CommandHandler.HistoryStorage historyStorage = new CommandHandler.HistoryStorage();
            MusicBandFieldsChecker musicBandFieldsChecker = new MusicBandFieldsChecker(reader);
            HashMap<String, Command> commands = new HashMap<>();
            commands.put("info", new InfoCommand(writer, arrayListManager));
            commands.put("show", new ShowCommand(writer, arrayListManager));
            commands.put("add", new AddCommand(arrayListManager, musicBandFieldsChecker));
            commands.put("update", new UpdateCommand(arrayListManager, musicBandFieldsChecker));
            commands.put("remove_by_id", new RemoveByIdCommand(arrayListManager));
            commands.put("clear", new ClearCommand(arrayListManager));
            //commands.put("save", new SaveCommand(arrayListManager, savingFile, writer));
            commands.put("exit", new ExitCommand());
            commands.put("insert_at", new InsertAtCommand(arrayListManager, musicBandFieldsChecker));
            commands.put("remove_last", new RemoveLastCommand(arrayListManager));
            commands.put("history", new HistoryCommand(writer, historyStorage));
            commands.put("count_greater_than_genre", new CountGreaterThanGenreCommand(writer, arrayListManager));
            commands.put("filter_less_than_singles_count", new FilterLessThanSinglesCountCommand(writer, arrayListManager));
            commands.put("print_field_descending_genre", new PrintFieldsDescendingGenreCommand(writer, arrayListManager));
            commands.put("help", new HelpCommand(writer, commands));
            CommandHandler commandHandler = new CommandHandler(commands, historyStorage);


            RequestReader requestReader = new RequestReader(socketChannel, ByteBuffer.allocate(1024));
            RequestHandler requestHandler = new RequestHandler(commandHandler, requestReader, writer, logger);
            requestHandler.run();
            saveCommand.execute("", new String[1]);
        } while (!singleIterationMode);
    }



}
