package ru.spbau.kononenko.task4.benchmarks;

import ru.spbau.kononenko.task4.comparables.MyComparable;
import ru.spbau.kononenko.task4.comparators.DefaultComparator;
import ru.spbau.kononenko.task4.sorters.Sorter;

import java.util.List;

abstract class ComparableBenchmark implements Benchmark {
    @Override
    public <T extends MyComparable<? super T>> long getTime(List<T> list, Sorter sorter) {
        return getTime(list, sorter, new DefaultComparator<T>());
    }

}
