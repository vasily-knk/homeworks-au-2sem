package ru.spbau.kononenko.task4;

import ru.spbau.kononenko.task4.benchmarks.Benchmark;
import ru.spbau.kononenko.task4.benchmarks.BenchmarkImpl;
import ru.spbau.kononenko.task4.comparables.ComparableInteger;
import ru.spbau.kononenko.task4.comparables.ComparableString;
import ru.spbau.kononenko.task4.comparators.IntegerModComparator;
import ru.spbau.kononenko.task4.comparators.MyComparator;
import ru.spbau.kononenko.task4.comparators.StringLengthComparator;
import ru.spbau.kononenko.task4.generators.Generator;
import ru.spbau.kononenko.task4.generators.RandomIntGenerator;
import ru.spbau.kononenko.task4.generators.RandomStringGenerator;
import ru.spbau.kononenko.task4.sorters.HeapSort;
import ru.spbau.kononenko.task4.sorters.ShakerSort;
import ru.spbau.kononenko.task4.sorters.Sorter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The main class.
 * @author Vasily Kononenko
 * @version %I%, %G%
*/
public class Main {
    /**
     * The entry point.
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Strings: ");
        test (new RandomStringGenerator(20), new ShakerSort(),
              new StringLengthComparator(), 1000);

        System.out.println("Integers: ");
        test (new RandomIntGenerator(10000), new HeapSort(),
                new IntegerModComparator(73), 1000);

    }

    private static <T> void test(Generator<T> generator, Sorter sorter,
                                 MyComparator<T> comparator, int count) {
        
        List<T> list = generator.generate(count);
        Benchmark benchmark = new BenchmarkImpl();
        long time = benchmark.getTime(list, sorter, comparator);
        System.out.println("Sorted " + count /*+ T.class.getName()*/
                           + " elements with " + sorter.getClass().getSimpleName()
                           + " using " + comparator.getClass().getSimpleName()
                           + " in " + time + "ms.");
    } 
}
