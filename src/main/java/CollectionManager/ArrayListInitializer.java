package CollectionManager;

import Console.ConsoleWriter;
import musicband.*;

import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

public class ArrayListInitializer {
    private Reader reader;
    private ArrayList<MusicBand> list;
    private LocalDate initializationDate;
    private Parser parser;
    private MusicBandFieldsReader fieldsReader;

    public ArrayListInitializer(Reader reader, MusicBandFieldsReader fieldsReader, Parser parser) {
        this.reader = reader;
        this.fieldsReader = fieldsReader;
        this.parser = parser;
    }


    public ArrayList<MusicBand> init() throws IOException {
        ArrayList<ArrayList<String>> data = parser.parseCSV(reader);
        ArrayList<MusicBand> musicBands = new ArrayList<>();
        Iterator<ArrayList<String>> iterator = data.iterator();
        Iterator<String> innerIteratorForDate;
        if (iterator.hasNext()) {
            innerIteratorForDate = iterator.next().iterator();
            if (innerIteratorForDate.hasNext()) {
                initializationDate = fieldsReader.readDate(innerIteratorForDate.next());
            }
        }
        for (;iterator.hasNext();) {
            Iterator<String> innerIterator = iterator.next().iterator();
            long id = 0;
            String name = "";
            Long x = Long.valueOf(0);
            Double y = Double.valueOf(0.0);
            Coordinates coordinates = null;
            LocalDate creationDate = null;
            int numberOfParticipants = 0;
            Integer singlesCount = 0;
            MusicGenre genre = null;
            Label label = null;
            if (innerIterator.hasNext()) id = fieldsReader.readId(innerIterator.next());
            if (innerIterator.hasNext()) name = fieldsReader.readName(innerIterator.next());
            if (innerIterator.hasNext()) x = fieldsReader.readX(innerIterator.next());
            if (innerIterator.hasNext()) { y = fieldsReader.readY(innerIterator.next()); coordinates = new Coordinates(x, y); }
            if (innerIterator.hasNext()) creationDate = fieldsReader.readDate(innerIterator.next());
            if (innerIterator.hasNext()) numberOfParticipants = fieldsReader.readNumberOfParticipants(innerIterator.next());
            if (innerIterator.hasNext()) singlesCount = fieldsReader.readSinglesCount(innerIterator.next());
            if (innerIterator.hasNext()) genre = fieldsReader.readMusicGenre(innerIterator.next());
            if (innerIterator.hasNext()) label = fieldsReader.readLabel(innerIterator.next());
            if (label != null) musicBands.add(new MusicBand(id, name, coordinates, creationDate, numberOfParticipants, singlesCount, genre, label));
        }
        return musicBands;
    }
}
