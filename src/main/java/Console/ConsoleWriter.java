package Console;


/**
 * Класс для консоли, осуществляющий вывод
 */
public class ConsoleWriter {
    public ConsoleWriter() {}

    /**
     * Выводит текст в стандартный поток вывода
     * @param string Текст
     */
    public void write(String string) {
        System.out.println(string);
    }
}
