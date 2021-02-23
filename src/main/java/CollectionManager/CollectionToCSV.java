package CollectionManager;

import java.util.ArrayList;
import java.util.Iterator;

public class CollectionToCSV {
    public static String toCSV(ArrayList<? extends CSVConvertable> arrayList) {
        String CSVString = "";
        for (Iterator<? extends CSVConvertable> it = arrayList.iterator(); it.hasNext(); ) {
            CSVString += it.next().toCSV() + "\n";
        }
        return CSVString;
    }
}
