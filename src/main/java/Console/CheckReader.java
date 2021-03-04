package Console;

import Console.Exсeptions.InputValueException;

public interface CheckReader<T> {
    T read() throws InputValueException;
}
