package ru.spbau.kononenko.task4.comparables;

/**
 * Interface for comparable types.
 * @param <T> the type of objects that this object may be compared to
 */
public interface MyComparable<T> {
    /**
     * Compares this object with the specified object.
     * @param x the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     *         is less than, equal to, or greater than the specified object.
     */
    int compareTo(T x);
}
