package ru.spbau.kononenko.task4.benchmarks;

import ru.spbau.kononenko.task4.comparables.MyComparable;
import ru.spbau.kononenko.task4.comparators.MyComparator;
import ru.spbau.kononenko.task4.sorters.Sorter;

import java.util.List;

/**
 * Benchmark interface for different sorters.
 * @author Vasily Kononenko
 * @version %I%, %G%
*/
public interface Benchmark {
    /**
     * Returns the time it took to sort the specified list using the specified sorter.
     * @param list list to sort
     * @param sorter sorter to use
     * @param <T> comparable type
     * @return sorting time
     */
    <T extends MyComparable<? super T>> long getTime (List<T> list, Sorter sorter);

    /**
     * Returns the time it took to sort the specified list
     * using the specified sorter and comparator.
     * @param list list to sort
     * @param sorter sorter to use
     * @param comparator comparator to use
     * @param <T> type to compare
     * @return sorting time
     */
    <T> long getTime(List<T> list, Sorter sorter, MyComparator<T> comparator);
}
