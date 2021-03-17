import java.io.File;

public class TestMain {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\Semyon\\Desktop\\script.txt");
        System.out.println(file.toPath());
    }
}
