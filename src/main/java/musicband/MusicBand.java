package musicband;

import java.time.LocalDate;

public class MusicBand implements Comparable<MusicBand>{
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int numberOfParticipants; //Значение поля должно быть больше 0
    private Integer singlesCount; //Поле может быть null, Значение поля должно быть больше 0
    private MusicGenre genre; //Поле может быть null
    private Label label; //Поле не может быть null

    public MusicBand(long id, String name, Coordinates coordinates, LocalDate creationDate, int numberOfParticipants, Integer singlesCount, MusicGenre genre, Label label) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.numberOfParticipants = numberOfParticipants;
        this.singlesCount = singlesCount;
        this.genre = genre;
        this.label = label;
    }


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public Integer getSinglesCount() {
        return singlesCount;
    }

    public MusicGenre getGenre() {
        return genre;
    }

    public Label getLabel() {
        return label;
    }


    @Override
    public int compareTo(MusicBand o) {
        return Long.compare(this.getId(), o.getId());
    }

    @Override
    public String toString() {
        return "MusicBand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates.getX() + " ; " + coordinates.getY() +
                ", creationDate=" + creationDate +
                ", numberOfParticipants=" + numberOfParticipants +
                ", singlesCount=" + singlesCount +
                ", genre=" + genre +
                ", label=" + label.getName() +
                '}';
    }
}





