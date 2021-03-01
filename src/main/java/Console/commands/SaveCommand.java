package Console.commands;

import CollectionManager.ArrayListManager;
import CollectionManager.CollectionToCSV;
import Console.ConsoleWriter;

import java.io.*;

/**
 * Класс команды save, сохраняющей коллекцию в файл
 */
public class SaveCommand extends AbstractCommand {

    ArrayListManager listManager;
    Writer writer;
    ConsoleWriter consoleWriter;
    File file;

    /**
     * @param listManager Менеджер коллекции
     * @param file Файл, в который будет сохрянятся коллекция
     * @param consoleWriter Объект класса, выводящего в консоль
     */
    public SaveCommand(ArrayListManager listManager, File file, ConsoleWriter consoleWriter) {
        super("save", "save collection into file");
        this.listManager = listManager;
        this.file = file;
        this.consoleWriter = consoleWriter;
    }

    /**
     * @param argument
     * @return CommandCode.DEFAULT
     */
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
                consoleWriter.write("Saving successfully done");
            } catch (IOException e) {
                consoleWriter.write("Unexpected save error");
            }
        } catch (FileNotFoundException | NullPointerException e) {
            consoleWriter.write("File not found");
        }
        return CommandCode.DEFAULT;
    }
}
