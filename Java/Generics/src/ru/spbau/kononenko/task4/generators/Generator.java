package ru.spbau.kononenko.task4.generators;

import java.util.List;

/**
 * Generator interface.
 * @param <T> the type of objects to generate.
 * @author Vasily Kononenko
 * @version %I%, %G%
 */
public interface Generator<T> {
    /**
     * Generates the objects list.
     * @param count the amount of objects to generate
     * @return the list of generated objects
     */
    List<T> generate(int count);
}
