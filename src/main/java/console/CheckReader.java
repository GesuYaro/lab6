package console;

import console.exсeptions.InputValueException;

public interface CheckReader<T> {
    T read() throws InputValueException;
}
