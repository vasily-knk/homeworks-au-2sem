package ru.spbau.kononenko.task6.serialization;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Interface for serialization via reflection.
 * @author Vasily Kononenko
 * @version %I%, %G%
*/
public interface ReflectionSerializerInterface {
    /**
     *
     * @param t the object to serialize
     * @param filename name of the file to write to
     * @param <T> the object type
     */
    <T> void serialize(T t, String filename) throws IOException;
}
