package CollectionManager;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

public class Parser {

    public ArrayList<ArrayList<String>> parseCSV(Reader reader) throws IOException {
        ArrayList<ArrayList<String>> list = new ArrayList<>();
        CSVParser parser = CSVParser.parse(reader,CSVFormat.DEFAULT);
        for (CSVRecord csvRecord : parser) {
            ArrayList<String> insideList = new ArrayList<>();
            for (String record : csvRecord) {
                insideList.add(record);
            }
            list.add(insideList);
        }
        return list;
    }

}
