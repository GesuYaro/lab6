package Console.commands;

import CollectionManager.ArrayListManager;
import CollectionManager.CollectionToCSV;
import Console.ConsoleWriter;

import java.io.*;

public class SaveCommand extends AbstractCommand {

    ArrayListManager listManager;
    Writer writer;
    ConsoleWriter consoleWriter;
    File file;

    public SaveCommand(ArrayListManager listManager, File file) {
        super("save", "save collection into file");
        this.listManager = listManager;
        this.file = file;
    }

    @Override
    public CommandCode execute(String argument) {
        try {
            writer = new OutputStreamWriter(new FileOutputStream(file));
            String CSVString = "";
            CSVString += listManager.getInitializationDate().toString() + "\n";
            CSVString += CollectionToCSV.toCSV(listManager.getArrayList());
            try {
                writer.write(CSVString);
                writer.flush();
            } catch (IOException e) {
                consoleWriter.write("Unexpected save error");
            }
        } catch (FileNotFoundException | NullPointerException e) {
            consoleWriter.write("File not found");
        }
        return CommandCode.DEFAULT;
    }
}
