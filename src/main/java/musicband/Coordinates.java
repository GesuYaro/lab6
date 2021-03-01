package musicband;

/**
 * Координаты
 */
public class Coordinates {
    private Long x; //Максимальное значение поля: 3, Поле не может быть null
    private Double y; //Значение поля должно быть больше -859, Поле не может быть null

    public Coordinates(Long x, Double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return Координата X
     */
    public Long getX() {
        return x;
    }

    /**
     * @return Координата Y
     */
    public Double getY() {
        return y;
    }

}
