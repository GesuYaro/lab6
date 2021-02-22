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
    public MusicBandFieldsReader(){}

    public long readId() throws InputValueExeption {
        long id;
            try {
                id = Long.parseLong(reader.readLine().trim());
            }
            catch (NumberFormatException e){
                throw new InputValueExeption();
            }
            catch (IOException e) {
                throw new InputValueExeption("Unexpected input error");
            }
        return id;
    }

    public long readId(String string) throws InputValueExeption {
        long id;
        try {
            id = Long.parseLong(string.trim());
            if (id <= 0) throw new InputValueExeption("ID must be long and greater than 0");
        }
        catch (NumberFormatException e){
            throw new InputValueExeption();
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
                else throw new InputValueExeption("Input Error\nField can't be null, string can't be empty");
            }
            catch (IOException e) {
                throw new InputValueExeption("Unexpected input error");
            }
        return name;
    }

    public String readName(String string) throws InputValueExeption {
        String name;
        String prename;
        prename = string.trim();
        if (!prename.equals("")) {
            name = prename;
        }
        else throw new InputValueExeption("Input Error\nField can't be null, string can't be empty");
        return name;
    }

    public long readX() throws InputValueExeption {
        long x;
        try {
            x = Long.parseLong(reader.readLine().trim());
            if (x > 3) throw new InputValueExeption("Field can't be greater than 3");
        }
        catch (NumberFormatException e){
            throw new InputValueExeption("Input Error\nField should be long");
        }
        catch (IOException e) {
            throw new InputValueExeption("Unexpected input error");
        }
        return x;
    }

    public long readX(String string) throws InputValueExeption {
        long x;
        try {
            x = Long.parseLong(string.trim());
            if (x > 3) throw new InputValueExeption("Field can't be greater than 3");
        }
        catch (NumberFormatException e){
            throw new InputValueExeption("Input Error\nField should be long");
        }
        return x;
    }

    public double readY() throws InputValueExeption {
        double y;
            try {
                y = Double.parseDouble(reader.readLine().trim());
                if (y < -859) throw new InputValueExeption("Field should be greater than -859");
            }
            catch (NumberFormatException e){
                throw new InputValueExeption("Input Error\nField should be double");
            }
            catch (IOException e) {
                throw new InputValueExeption("Unexpected input error");
            }
        return y;
    }

    public double readY(String string) throws InputValueExeption {
        double y;
        try {
            y = Double.parseDouble(string.trim());
            if (y < -859) throw new InputValueExeption("Field should be greater than -859");
        }
        catch (NumberFormatException e){
            throw new InputValueExeption("Input Error\nField should be double");
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
            throw new InputValueExeption("Input Error\nEnter date in YYYY-MM-DD format");
        } catch (IOException e) {
            throw new InputValueExeption("Unexpected input error");
        }
        return date;
    }

    public LocalDate readDate(String string) throws InputValueExeption {
        LocalDate date;
        try {
            date = LocalDate.parse(string.trim());
        } catch (DateTimeParseException e) {
            throw new InputValueExeption("Input Error\nEnter date in YYYY-MM-DD format");
        }
        return date;
    }

    public int readNumberOfParticipants() throws InputValueExeption {
        int numberOfParticipants;
            try {
                numberOfParticipants = Integer.parseInt(reader.readLine().trim());
                if (numberOfParticipants <= 0) throw new NumberFormatException();
            }
            catch (NumberFormatException e){
                throw new InputValueExeption("Input Error\nEnter the natural number");
            }
            catch (IOException e) {
                throw new InputValueExeption("Unexpected input error");
            }
        return numberOfParticipants;
    }

    public int readNumberOfParticipants(String string) throws InputValueExeption {
        int numberOfParticipants;
        try {
            numberOfParticipants = Integer.parseInt(string.trim());
            if (numberOfParticipants <= 0) throw new NumberFormatException();
        }
        catch (NumberFormatException e){
            throw new InputValueExeption("Input Error\nEnter the natural number");
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
                throw new InputValueExeption("Input Error\nEnter the natural number");
            }
            catch (IOException e) {
                throw new InputValueExeption("Unexpected input error");
            }
        return singlesCount;
    }

    public Integer readSinglesCount(String string) throws InputValueExeption {
        Integer singlesCount = null;
        try {
            String str = string.trim();
            if (!str.equals("")) {
                singlesCount = Integer.parseInt(str);
            }
        }
        catch (NumberFormatException e){
            throw new InputValueExeption("Input Error\nEnter the natural number");
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
                        throw new InputValueExeption("Input Error\nGenre should be chosen from list");
                    }
                }
            }
            catch (IOException e) {
                throw new InputValueExeption("Unexpected input error");
            }

        return choicedMusicGenre;
    }

    public MusicGenre readMusicGenre(String string) throws InputValueExeption {
        String str;
        MusicGenre choicedMusicGenre = null;
            str = string.trim().toUpperCase();
            if (!str.equals("")) {
                for(MusicGenre musicGenre : MusicGenre.values()) {
                    if (musicGenre.name().equals(str)) {
                        choicedMusicGenre = musicGenre;
                    }

                }
                if (choicedMusicGenre == null) {
                    throw new InputValueExeption("Input Error\nGenre should be chosen from list");
                }
            }
        return choicedMusicGenre;
    }

    public Label readLabel() throws InputValueExeption {
        Label label = new Label();
        try {
            label.setName(reader.readLine().trim());
        }
        catch (IOException e) {
            throw new InputValueExeption("Unexpected input error");
        }
        return label;
    }

    public Label readLabel(String string) throws InputValueExeption {
        Label label = new Label();
            label.setName(string.trim());
        return label;
    }

    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }
}
