import Console.Console;
import Console.InteractiveModeReader;
import musicband.InputValueExeption;
import musicband.MusicBandFieldsReader;
import musicband.MusicGenre;
import Console.CommandHandler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;

public class TestMain {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        MusicBandFieldsReader mbfReader = new MusicBandFieldsReader(reader);
        Console console = new Console(new CommandHandler(), new BufferedReader(new InputStreamReader(System.in)));
        InteractiveModeReader interactiveModeReader = new InteractiveModeReader(mbfReader, console);
        System.out.println(interactiveModeReader.readlabel());

    }
}
