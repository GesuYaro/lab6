package musicband;

public class Coordinates {
    private Long x; //Максимальное значение поля: 3, Поле не может быть null
    private Double y; //Значение поля должно быть больше -859, Поле не может быть null

    public Coordinates(Long x, Double y) {
        this.x = x;
        this.y = y;
    }

    public Long getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    //    public void setX(Long x) {
//        if (x != null && x <= 3) {
//            this.x = x;
//        }
//        else { throw new InputValueExeption("Максимальное значение поля: 3, Поле не может быть null"); }
//    }
//
//    public void setY(Double y) {
//        if (y != null && y > -859) {
//            this.y = y;
//        }
//        else { throw new InputValueExeption("Значение поля должно быть больше -859, Поле не может быть null"); }
//    }
}
