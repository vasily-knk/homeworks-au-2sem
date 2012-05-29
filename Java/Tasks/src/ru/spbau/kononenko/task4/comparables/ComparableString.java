package ru.spbau.kononenko.task4.comparables;

import ru.spbau.kononenko.task4.Keeper;

public class ComparableString extends Keeper<String> implements MyComparable<ComparableString> {

    public ComparableString(String value) {
        super(value);
    }

    @Override
    public int compareTo(ComparableString x) {
        return value.compareTo(x.value);
    }
}
