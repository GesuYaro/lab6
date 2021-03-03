package Console;

import Console.Exсeptions.InputValueException;

public interface CheckReader {
    Object read() throws InputValueException;
}
