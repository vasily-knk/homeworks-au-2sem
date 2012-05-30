package ru.spbau.kononenko.task6;

/**
 * Interface for serialization via reflection.
 */
public interface ReflectionSerializerInterface {
    /**
     *
     * @param t the object to serialize
     * @param filename name of the file to write to
     * @param <T> the object type
     */
    <T> void serialize(T t, String filename);
}
