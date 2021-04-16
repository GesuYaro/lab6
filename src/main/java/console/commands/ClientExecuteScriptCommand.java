package console.commands;

import client.Console;
import client.RequestFabric;
import client.RequestWriter;
import client.ResponseReader;
import collectionManager.ArrayListManager;
import console.*;
import console.Writer;
import console.exсeptions.IncorrectScriptException;
import console.exсeptions.NoArgumentFoundException;
import musicband.MusicBandFieldsChecker;
import console.CommandHandler.HistoryStorage;

import java.io.*;
import java.nio.file.Files;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Stream;

public class ClientExecuteScriptCommand extends AbstractCommand {

    private ConsoleWriter writer;
    private HashSet<File> scripts;
    private boolean isInnerScript;
    private Collection<String> commandsWithExtendedRequest;
    private RequestWriter requestWriter;
    private ResponseReader responseReader;

    public ClientExecuteScriptCommand(ConsoleWriter writer, Collection<String> commandsWithExtendedRequest, RequestWriter requestWriter, ResponseReader responseReader) {
        super("execute_script", "Execute script from the file");
        this.writer = writer;
        this.commandsWithExtendedRequest = commandsWithExtendedRequest;
        this.requestWriter = requestWriter;
        this.responseReader = responseReader;
        this.isInnerScript = false;
        this.scripts = new HashSet<>();
    }

    public ClientExecuteScriptCommand(ConsoleWriter writer, Collection<String> commandsWithExtendedRequest, RequestWriter requestWriter, ResponseReader responseReader, HashSet<File> scripts) {
        super("execute_script", "Execute script from the file");
        this.writer = writer;
        this.commandsWithExtendedRequest = commandsWithExtendedRequest;
        this.requestWriter = requestWriter;
        this.responseReader = responseReader;
        this.isInnerScript = true;
        this.scripts = scripts;
    }

    @Override
    public CommandCode execute(String firstArgument, String[] arguments) throws NoArgumentFoundException {
        String path = firstArgument.trim();
        if (path.equals("")) throw new NoArgumentFoundException();
        File file = new File(path);
        try {
            if (file.exists()) {
                if (file.canRead()) {
                    if (!scripts.contains(file)) {
                        scripts.add(file);
                        if (containsExit(file)) {
                            BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                            MusicBandFieldsChecker fieldsReader = new MusicBandFieldsChecker(fileReader);
                            FieldsReader scriptModeReader = new FieldsReader(fieldsReader, writer, false);
                            RequestFabric requestFabric = new RequestFabric(commandsWithExtendedRequest, scriptModeReader);
                            ClientExecuteScriptCommand innerExecuteScriptCommand = new ClientExecuteScriptCommand(writer, commandsWithExtendedRequest, requestWriter, responseReader, scripts);
                            client.Console console = new Console(requestWriter, responseReader, requestFabric, fileReader, innerExecuteScriptCommand);
                            try {
                                console.run();
                            } catch (IncorrectScriptException e) {
                                writer.write(e.getMessage());
                            } catch (NullPointerException | StackOverflowError e) {
                                writer.write("Error. Incorrect Script");
                            }
                        } else {
                            writer.write("Script doesn't contain exit. It wouldn't be executed.");
                        }
                    } else {
                        writer.write("Recursion found. Stopping the script");
                    }
                } else {
                    writer.write("Access denied. Can't read the file");
                }
            } else {
                writer.write("Can't access the file " + path);
            }
        } catch (FileNotFoundException | SecurityException e) {
            writer.write("Can't access the file " + path);
        } catch (IOException e) {
            writer.write("Unexpected error with file");
        }
        if (!isInnerScript) scripts.clear();
        else scripts.remove(file);
        return CommandCode.DEFAULT;
    }

    private boolean containsExit(File file) throws IOException {
        Stream<String> stream = Files.lines(file.toPath());
        boolean contains = false;
        if (stream != null) contains = stream.anyMatch(str -> str != null && str.trim().equals("exit"));
        return contains;
    }
}

