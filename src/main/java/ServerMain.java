import collectionManager.ArrayListInitializer;
import collectionManager.ArrayListManager;
import collectionManager.Parser;
import console.*;
import console.commands.Command;
import console.commands.*;
import musicband.MusicBand;
import musicband.MusicBandFieldsChecker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.*;
import server.Console;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * Главный класс, в котором создаются объекты и запускается приложение
 */
public class ServerMain {

    private static int PORT = 691;
    public static final Logger logger = LoggerFactory.getLogger("Server");

    public static void main(String[] args) {

        logger.info("App is turned on.");
        ConsoleWriter writer = new ConsoleWriter();
        ArrayList<MusicBand> musicBandArrayList = new ArrayList<>();
        LocalDate initializationDate = LocalDate.now();
        File file = null;
        String path;
        try {
            path = System.getenv("LAB5_PATH");
            if (path != null) {
                path = path.replace("\u202A", "");
                file = new File(path);
                if (!file.exists()) {
                    file.createNewFile();
                }
                if (file.canRead() && file.canWrite()) {
                    BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                    ArrayListInitializer arrayListInitializer = new ArrayListInitializer(fileReader, new MusicBandFieldsChecker(), new Parser());
                    try {
                        musicBandArrayList = arrayListInitializer.init();
                    } catch (IllegalStateException e) {
                        logger.info("Incorrect file");
                    }
                    if (arrayListInitializer.getInitializationDate() != null)
                        initializationDate = arrayListInitializer.getInitializationDate();
                }
                else logger.error("Access denied. Can't read or write file");
            }
            else logger.error("Error with environment variable. Variable LAB5_PATH not found");
        } catch (SecurityException e) {
            logger.error("File not found. Access denied. Can't create a new file.");
        } catch (FileNotFoundException e) {
            logger.error("File not found");
        } catch (IOException e) {
            logger.error("Unexpected error with initialization");
        }


        ArrayListManager arrayListManager = new ArrayListManager(musicBandArrayList, initializationDate);
        if (arrayListManager.containsRepeatingId()) {  // проверяем, есть ли объекты с одинаковым id
            logger.error("Error. Collection contains repeating id");
            arrayListManager.clear();
        }

        Connector connector = null;
        try {
            connector = new Connector(PORT);
            server.Console console = new Console(arrayListManager, file, connector, logger);
            console.run(args != null && args.length > 0 && args[0].equals("s"));
        } catch (IOException e) {
            logger.error("Can't make a server");
        }

    }
}
