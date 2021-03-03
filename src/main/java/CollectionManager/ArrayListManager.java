package CollectionManager;

import Console.Exсeptions.NoSuchIdException;
import musicband.MusicBand;
import musicband.MusicGenre;

import java.time.LocalDate;
import java.util.*;

/**
 * Менеджер коллекции
 */
public class ArrayListManager {
    private ArrayList<MusicBand> arrayList;
    private LocalDate initializationDate;
    private long maxId;

    /**
     * @param arrayList Коллекция, которой будет управлять менеджер
     * @param initializationDate Дата инициализации коллекции
     */
    public ArrayListManager(ArrayList<MusicBand> arrayList, LocalDate initializationDate) {
        this.arrayList = arrayList;
        this.initializationDate = initializationDate;
    }

    /**
     * @return Информация о коллекции
     */
    public String info() {
        String str = "";
        str += "Type: ArrayList<MusicBand>"
                + "\nInitialization date: " + initializationDate.toString()
                + "\nNumber of elements: " + arrayList.size();
        return str;
    }

    /**
     * @return Коллекцию, которой управляет менеджер
     */
    public ArrayList<MusicBand> getArrayList() {
        return arrayList;
    }

    /**
     * @param musicBand Добавляет объект в коллекцию
     */
    public void add(MusicBand musicBand) {
        arrayList.add(musicBand);
    }

    /**
     * Сортирует коллекцию
     */
    public void sort() {
        Comparator<MusicBand> comparator = (MusicBand::compareTo);
        arrayList.sort(comparator);
    }

    /**
     * Удаляет элемент из коллекции
     * @param musicBand
     */
    public void remove(MusicBand musicBand) {
        arrayList.remove(musicBand);
    }

    /**
     * Очищает коллекцию
     */
    public void clear() {
        arrayList.clear();
    }

    /**
     * Удаляет последний элемент коллекции
     */
    public void removeLast() {
        if (arrayList.size() > 0) {
            arrayList.remove(arrayList.size() - 1);
        }
    }

    /**
     * @return Элементы коллекции в строковом представлении
     */
    public String show() {
        String str = "";
        for (ListIterator<MusicBand> iterator = arrayList.listIterator(); iterator.hasNext(); ) {
             str += iterator.next().toString() + "\n";
        }
        return str;
    }

    /**
     * @param index Индекс элемента коллекции
     * @return Элемент коллекции, найденный по индексу
     */
    public MusicBand get(int index) {
        return arrayList.get(index);
    }

    /**
     * @param index Индекс куда вставить элемент
     * @param musicBand Объект, который нужно вставить в коллекцию
     */
    public void set(int index, MusicBand musicBand) {
        arrayList.set(index, musicBand);
    }

    /**
     * @param id
     * @return Объект с id, равным заданому
     * @throws NoSuchIdException
     */
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
            throw new NoSuchIdException();
        }
        return musicBand;
    }

    /**
     * @param id id элемента, который нужно заменить
     * @param musicBand Объект, который нужно вставить на место замененного
     */
    public void replace(long id, MusicBand musicBand) {
        for (ListIterator<MusicBand> iterator = arrayList.listIterator(); iterator.hasNext(); ) {
            MusicBand mb = iterator.next();
            if (mb.getId() == id) {
                arrayList.set(arrayList.indexOf(mb), musicBand);
                break;
            }
        }
    }

    /**
     * @param id id объекта, который нужно удалить
     */
    public void removeById(long id) {
        for (ListIterator<MusicBand> iterator = arrayList.listIterator(); iterator.hasNext(); ) {
            MusicBand mb = iterator.next();
            if (mb.getId() == id) {
                arrayList.remove(mb);
                break;
            }
        }
    }

    /**
     * @param index Индекс, в который нужно вставить элемент
     * @param musicBand Объект, который нужно вставить
     */
    public void insertAtIndex(int index, MusicBand musicBand) {
        arrayList.add(index, musicBand);
    }

    /**
     * @param genre Жанр
     * @return Количество элементов, поле genre, которых превышает введенный жанр
     */
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

    /**
     * @param singlesCount
     * @return Коллекция объектов, поле singlesCount которых меньше введенной
     */
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

    /**
     * @return Отсортированную коллекцию по полю genre
     */
    public ArrayList<MusicBand> sortByGenre() {
        ArrayList<MusicBand> sortedList = (ArrayList<MusicBand>) arrayList.clone();
        sortedList.sort((o1, o2) -> o1.getGenre().compareTo(o2.getGenre()));
        return sortedList;
    }

    /**
     * @return Максимальный id в коллекции
     */
    public long getMaxId() {
        return maxId;
    }

    /**
     * Увеличивает максимальный id
     */
    public void increaseMaxId() {
        if (arrayList.size() > 0) {
            for (MusicBand mb : arrayList) {
                if (mb.getId() > this.maxId) {
                    this.maxId = mb.getId() + 1;
                }
            }
        }
        else this.maxId = 1;
    }


    /**
     * @return Дата инициализации коллекции
     */
    public LocalDate getInitializationDate() {
        return initializationDate;
    }

    /**
     * @return true, если в коллекции обнаружены элементы с одинаковым id
     */
    public boolean containsRepeatingId() {
        boolean isRepeating = false;
        HashSet<Long> ids = new HashSet<>();
        for (ListIterator<MusicBand> iterator = arrayList.listIterator(); iterator.hasNext(); ) {
            MusicBand mb = iterator.next();
            if (ids.contains(mb.getId())) {
                isRepeating = true;
                break;
            }
            ids.add(mb.getId());
        }
        return isRepeating;
    }
}
