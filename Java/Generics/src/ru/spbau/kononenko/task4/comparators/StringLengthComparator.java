package ru.spbau.kononenko.task4.comparators;

import ru.spbau.kononenko.task4.comparables.ComparableString;

/**
 * Comparator to compare string lengths.
 */
public class StringLengthComparator implements MyComparator<ComparableString> {
    @Override
    public int compare(ComparableString x, ComparableString y) {
        return x.getValue().length() - y.getValue().length();
    }
}
