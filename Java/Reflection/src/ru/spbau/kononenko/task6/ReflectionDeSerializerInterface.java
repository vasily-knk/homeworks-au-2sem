package ru.spbau.kononenko.task6;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Interface for deserialization via reflection.
 */
public interface ReflectionDeSerializerInterface {
    /**
     * Deserializes object from the file specified.
     * @param filename name of the source file
     * @param clazz object class
     * @param <T> object type
     * @return the deserialized object
     */
    <T> T deserialize(String filename, Class<T> clazz) throws IOException;
}
