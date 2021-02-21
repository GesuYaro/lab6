package CollectionManager;

import Console.Exeptions.NoSuchIdExeption;
import musicband.MusicBand;
import musicband.MusicGenre;

import java.time.LocalDate;
import java.util.*;

public class ArrayListManager {
    private ArrayList<MusicBand> arrayList;
    private LocalDate initializationDate;
    private long maxId;

    public ArrayListManager(ArrayList<MusicBand> arrayList, LocalDate initializationDate) {
        this.arrayList = arrayList;
        this.initializationDate = initializationDate;
    }

    public String info() {
        String str = "";
        str += "Type: ArrayList<MusicBand>"
                + "\nInitialization date: " + initializationDate.toString()
                + "\nNumber of elements: " + arrayList.size();
        return str;
    }

    public ArrayList<MusicBand> getArrayList() {
        return arrayList;
    }

    public void add(MusicBand musicBand) {
        arrayList.add(musicBand);
    }

    public void sort() {
        Comparator<MusicBand> comparator = ((o1, o2) -> o1.compareTo(o2));
        arrayList.sort(comparator);
    }

    public void remove(MusicBand musicBand) {
        arrayList.remove(musicBand);
    }

    public void clear() {
        arrayList.clear();
    }

    public void removeLast() {
        if (arrayList.size() > 0) {
            arrayList.remove(arrayList.size() - 1);
        }
    }

    public String show() {
        String str = "";
        for (ListIterator<MusicBand> iterator = arrayList.listIterator(); iterator.hasNext(); ) {
             str += iterator.next().toString() + "\n";
        }
        return str;
    }

    public MusicBand get(int index) {
        return arrayList.get(index);
    }

    public MusicBand set(int index, MusicBand musicBand) {
        return arrayList.set(index, musicBand);
    }

    public MusicBand getById(long id) {
        MusicBand musicBand = null;
        for (ListIterator<MusicBand> iterator = arrayList.listIterator(); iterator.hasNext(); ) {
            MusicBand mb = iterator.next();
            if (mb.getId() == id) {
                musicBand = mb;
                break;
            }
        }
        if (musicBand == null) {
            throw new NoSuchIdExeption();
        }
        return musicBand;
    }

    public void replace(long id, MusicBand musicBand) {
        for (ListIterator<MusicBand> iterator = arrayList.listIterator(); iterator.hasNext(); ) {
            MusicBand mb = iterator.next();
            if (mb.getId() == id) {
                arrayList.set(arrayList.indexOf(mb), musicBand);
                break;
            }
        }
    }

    public void removeById(long id) {
        for (ListIterator<MusicBand> iterator = arrayList.listIterator(); iterator.hasNext(); ) {
            MusicBand mb = iterator.next();
            if (mb.getId() == id) {
                arrayList.remove(mb);
                break;
            }
        }
    }

    public void insertAtIndex(int index, MusicBand musicBand) {
        arrayList.add(index, musicBand);
    }

    public int countGreaterThanGenre(MusicGenre genre) {
        int count =0;
        for (ListIterator<MusicBand> iterator = arrayList.listIterator(); iterator.hasNext(); ) {
            MusicBand mb = iterator.next();
            if (mb.getGenre().compareTo(genre) < 0) {
                count += 1;
            }
        }
        return count;
    }

    public ArrayList<MusicBand> filterLessThanSinglesCount(Integer singlesCount) {
        ArrayList<MusicBand> list = new ArrayList<>();
        for (ListIterator<MusicBand> iterator = arrayList.listIterator(); iterator.hasNext(); ) {
            MusicBand mb = iterator.next();
            if (mb.getSinglesCount().compareTo(singlesCount) > 0) {
                list.add(mb);
            }
        }
        return list;
    }

    public ArrayList<MusicBand> sortByGenre() {
        ArrayList<MusicBand> sortedList = (ArrayList<MusicBand>) arrayList.clone();
        sortedList.sort((o1, o2) -> o1.getGenre().compareTo(o2.getGenre()));
        return sortedList;
    }

    public long getMaxId() {
        return maxId;
    }

    public void increaseMaxId() {
        if (arrayList.size() > 0) {
            ArrayList<MusicBand> clone = ((ArrayList<MusicBand>) arrayList.clone());
            clone.sort((o1, o2) -> o1.compareTo(o2));
            this.maxId = clone.get(clone.size() - 1).getId() + 1;
        }
        else this.maxId = 1;
    }
}
