package ru.spbau.kononenko.task4.comparators;

/**
 * An exception thrown when trying to create an IntegerModComparator
 * with zero as a mod value.
 * @author Vasily Kononenko
 * @version %I%, %G%
*/
public class ZeroModException extends RuntimeException {
    /**
     * Initializes this exception.
     * @param message the message string
     */
    public ZeroModException(String message) {
        super(message);
    }
}
