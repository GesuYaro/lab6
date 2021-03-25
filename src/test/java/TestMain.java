import musicband.Coordinates;
import musicband.Label;
import musicband.MusicBand;
import musicband.MusicGenre;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.*;
import java.time.LocalDate;

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

    }
}
