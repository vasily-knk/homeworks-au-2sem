package ru.spbau.kononenko.task4.comparators;

/**
 * Comparator interface.
 * @param <T>  the type of objects that may be compared by this comparator
 */
public interface MyComparator<T> {
    /**
     * Compares its two arguments.
     * @param x the first object to be compared.
     * @param y the second object to be compared.
     * @return a negative integer, zero, or a positive integer
     *         as the first argument is less than, equal to, or greater than the second.
     */
    int compare(T x, T y);
}
