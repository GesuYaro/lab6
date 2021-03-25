import collectionManager.Parser;
import musicband.Coordinates;
import musicband.Label;
import musicband.MusicBand;
import musicband.MusicGenre;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class TestMain {
    public static void main(String[] args) {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(System.out);
        Coordinates coordinates = new Coordinates(1L,1.0);
        Label label = new Label();
        label.setName("aaa,a");
        System.out.println("\"");
        try {
            CSVPrinter printer = new CSVPrinter(System.out, CSVFormat.RFC4180);
            printer.printRecord(new MusicBand(1, "\"", coordinates, LocalDate.MIN, 1, 1, MusicGenre.BLUES, label));
        } catch (IOException e) {
            System.out.println(111111111);
        }

//        CSVParser parser = null;
//        try {
//            parser = CSVParser.parse("\"\"\"1,\",2", CSVFormat.RFC4180);
//            for (CSVRecord csvRecord : parser) {
//                System.out.println(csvRecord);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        Parser parser = new Parser();
        ArrayList<ArrayList<String>> list = null;
        try {
            list = parser.parseCSV("MusicBand{id=1, name='\"\"', coordinates=1 ; 1.0, creationDate=-999999999-01-01, numberOfParticipants=1, singlesCount=1, genre=BLUES, label=aaa,a}");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (ArrayList<String> il: list
             ) {
            for (String iis: il
                 ) {
                System.out.println(iis);
            }

        }


    }
}
