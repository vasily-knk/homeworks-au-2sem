package ru.spbau.kononenko.task4.benchmarks;

import ru.spbau.kononenko.task4.comparators.MyComparator;
import ru.spbau.kononenko.task4.sorters.Sorter;

import java.util.List;

/**
 * The default benchmark implementation.
 */
public class BenchmarkImpl extends ComparableBenchmark {
    public <T> long getTime(List<T> list, Sorter sorter, MyComparator<T> comparator) {
        long startTime = System.currentTimeMillis();
        sorter.sort(list, comparator);
        return System.currentTimeMillis() - startTime;
    }
}
