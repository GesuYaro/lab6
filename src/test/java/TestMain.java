import CollectionManager.ArrayListInitializer;
import CollectionManager.Parser;
import Console.ConsoleWriter;
import Console.FieldsReader;
import musicband.MusicBand;
import musicband.MusicBandFieldsChecker;
import musicband.MusicGenre;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TestMain {
    public static void main(String[] args) {
        ConsoleWriter consoleWriter = new ConsoleWriter();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        MusicBandFieldsChecker checker = new MusicBandFieldsChecker(bufferedReader);
        FieldsReader fieldsReader = new FieldsReader(checker, consoleWriter);
        MusicGenre genre = fieldsReader.readMusicGenre();
        System.out.println(genre);
    }
}
