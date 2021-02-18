package musicband;


import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class MusicBandFieldsReader {
    private BufferedReader reader;
    public MusicBandFieldsReader(BufferedReader reader) {
        this.reader = reader;
    }

    public long readId() throws InputValueExeption {
        long id;
            try {
                id = Long.parseLong(reader.readLine().trim());
            }
            catch (NumberFormatException e){
                throw new InputValueExeption();
            }
            catch (IOException e) {
                throw new InputValueExeption("Непредвиденная ошибка ввода");
            }
        return id;
    }

    public String readName() throws InputValueExeption {
        String name;
        String prename;
            try {
                prename = reader.readLine().trim();
                if (!prename.equals("")) {
                    name = prename;
                }
                else throw new InputValueExeption("Ошибка ввода\nПоле не может быть null, Строка не может быть пустой");
            }
            catch (IOException e) {
                throw new InputValueExeption("Непредвиденная ошибка ввода");
            }
        return name;
    }

    public long readX() throws InputValueExeption {
        long x;
            try {
                x = Long.parseLong(reader.readLine().trim());
            }
            catch (NumberFormatException e){
                throw new InputValueExeption("Ошибка ввода\nПоле должно быть целым числом");
            }
            catch (IOException e) {
                throw new InputValueExeption("Непредвиденная ошибка ввода");
            }
        return x;
    }

    public double readY() throws InputValueExeption {
        double y;
            try {
                y = Double.parseDouble(reader.readLine().trim());
            }
            catch (NumberFormatException e){
                throw new InputValueExeption("Ошибка ввода\nПоле должно быть действительным числом с плавающей точкой");
            }
            catch (IOException e) {
                throw new InputValueExeption("Непредвиденная ошибка ввода");
            }
        return y;
    }

    public Coordinates readCoordinates() throws InputValueExeption {
        Long x = this.readX();
        Double y = this.readY();
        return new Coordinates(x, y);
    }

    public LocalDate readDate() throws InputValueExeption {
        LocalDate date;
        try {
            date = LocalDate.parse(reader.readLine().trim());
        } catch (DateTimeParseException e) {
            throw new InputValueExeption("Ошибка ввода\nВведите дату в формате YYYY-MM-DD");
        } catch (IOException e) {
            throw new InputValueExeption("Непредвиденная ошибка ввода");
        }
        return date;
    }

    public int readNumberOfParticipants() throws InputValueExeption {
        int numberOfParticipants;
            try {
                numberOfParticipants = Integer.parseInt(reader.readLine().trim());
            }
            catch (NumberFormatException e){
                throw new InputValueExeption("Ошибка ввода\nВведите натуральное число");
            }
            catch (IOException e) {
                throw new InputValueExeption("Непредвиденная ошибка ввода");
            }
        return numberOfParticipants;
    }

    public Integer readSinglesCount() throws InputValueExeption {
        Integer singlesCount = null;
            try {
                String str = reader.readLine().trim();
                if (!str.equals("")) {
                    singlesCount = Integer.parseInt(str);
                }
            }
            catch (NumberFormatException e){
                throw new InputValueExeption("Ошибка ввода\nВведите натуральное число");
            }
            catch (IOException e) {
                throw new InputValueExeption("Непредвиденная ошибка ввода");
            }
        return singlesCount;
    }

    public MusicGenre readMusicGenre() throws InputValueExeption {
        String str;
        MusicGenre choicedMusicGenre = null;
            try {
                str = reader.readLine().trim().toUpperCase();
                if (!str.equals("")) {
                    for(MusicGenre musicGenre : MusicGenre.values()) {
                        if (musicGenre.name().equals(str)) {
                            choicedMusicGenre = musicGenre;
                        }

                    }
                    if (choicedMusicGenre == null) {
                        throw new InputValueExeption("Ошибка ввода\nВыберите жанр из списка");
                    }
                }
            }
            catch (IOException e) {
                throw new InputValueExeption("Непредвиденная ошибка ввода");
            }

        return choicedMusicGenre;
    }

    public Label readLabel() throws InputValueExeption {
        Label label = new Label();
        try {
            label.setName(reader.readLine().trim());
        }
        catch (IOException e) {
            throw new InputValueExeption("Непредвиденная ошибка ввода");
        }
        return label;
    }

}
