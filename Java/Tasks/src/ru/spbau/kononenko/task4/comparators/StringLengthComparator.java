package ru.spbau.kononenko.task4.comparators;

public class StringLengthComparator implements MyComparator<String> {
    @Override
    public int compare(String x, String y) {
        return x.length() - y.length();
    }
}
