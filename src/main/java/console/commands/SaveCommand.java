package console.commands;

import collectionManager.ArrayListManager;
import collectionManager.CollectionToCSV;
import console.ConsoleWriter;
import musicband.MusicBand;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.*;

/**
 * Класс команды save, сохраняющей коллекцию в файл
 */
public class SaveCommand extends AbstractCommand {

    private ArrayListManager listManager;
    private Writer writer;
    private console.Writer consoleWriter;
    private File file;

    /**
     * @param listManager Менеджер коллекции
     * @param file Файл, в который будет сохрянятся коллекция
     * @param consoleWriter Объект класса, выводящего в консоль
     */
    public SaveCommand(ArrayListManager listManager, File file, console.Writer consoleWriter) {
        super("save", "save collection into file");
        this.listManager = listManager;
        this.file = file;
        this.consoleWriter = consoleWriter;
    }

    /**
     * @param firstArgument
     * @return CommandCode.DEFAULT
     */
    @Override
    public CommandCode execute(String firstArgument, String[] arguments) {
        try {
            writer = new OutputStreamWriter(new FileOutputStream(file));
            try {
                writer.write(listManager.getInitializationDate().toString() + "\n");
                CSVPrinter printer = new CSVPrinter(writer, CSVFormat.RFC4180);
                for (MusicBand musicBand: listManager.getArrayList()) {
                    printer.printRecord(musicBand.getId(), musicBand.getName(), musicBand.getCoordinates().getX(), musicBand.getCoordinates().getY(), musicBand.getCreationDate(), musicBand.getNumberOfParticipants(), ((musicBand.getSinglesCount()) != null) ? musicBand.getSinglesCount() : "", ((musicBand.getGenre() != null) ? musicBand.getGenre() : ""), ((musicBand.getLabel().getName() != null) ? musicBand.getLabel().getName() : ""));
                }
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
