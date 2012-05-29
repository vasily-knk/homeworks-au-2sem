package ru.spbau.kononenko.task4.comparators;

import ru.spbau.kononenko.task4.comparables.MyComparable;

/**
 * Default comparator for comparable objects.
 * @param <T> the comparable object type
 */
public class DefaultComparator<T extends MyComparable<? super T>> implements MyComparator<T> {

    @Override
    public int compare(T x, T y) {
        return x.compareTo(y);
    }
}
