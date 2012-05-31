package ru.spbau.kononenko.task6.serialization;

import ru.spbau.kononenko.task6.property.PropertyNotFoundException;
import ru.spbau.kononenko.task6.property.UnsupportedPropertyTypeException;

import java.io.IOException;

/**
 * Interface for deserialization via reflection.
 * @author Vasily Kononenko
 * @version %I%, %G%
*/
public interface ReflectionDeSerializerInterface {
    /**
     * Deserializes object from the file specified.
     * @param filename name of the source file
     * @param clazz object class
     * @param <T> object type
     * @return the deserialized object
     */
    <T> T deserialize(String filename, Class<T> clazz) throws IOException, InstantiationException, IllegalAccessException, UnsupportedPropertyTypeException, PropertyNotFoundException;
}
