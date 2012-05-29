package ru.spbau.kononenko.task4;

import ru.spbau.kononenko.task4.comparables.ComparableInteger;
import ru.spbau.kononenko.task4.comparables.ComparableString;
import ru.spbau.kononenko.task4.comparables.MyComparable;
import ru.spbau.kononenko.task4.comparators.MyComparator;
import ru.spbau.kononenko.task4.comparators.DefaultComparator;
import ru.spbau.kononenko.task4.sorters.DummySorter;
import ru.spbau.kononenko.task4.sorters.HeapSort;
import ru.spbau.kononenko.task4.sorters.Sorter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        List<ComparableInteger> list = new ArrayList<ComparableInteger>();
        Random random = new Random();
        for (int i = 0; i < 10; ++i)
            list.add(new ComparableInteger(random.nextInt(100)));

        for (ComparableInteger i : list)
            System.out.print(i.getValue() + " ");
        System.out.println();

        Sorter s = new HeapSort();
        s.sort(list);

        for (ComparableInteger i : list)
            System.out.print(i.getValue() + " ");
        System.out.println();
    }
}
