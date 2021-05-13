package console;

import musicband.InputValueException;

public interface CheckReader<T> {
    T read() throws InputValueException;
}
