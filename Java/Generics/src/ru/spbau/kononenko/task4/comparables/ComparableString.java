package ru.spbau.kononenko.task4.comparables;

public class ComparableString extends Keeper<String> implements MyComparable<ComparableString> {

    /**
     * Initializes this compared string
     * @param value string value
     */
    public ComparableString(String value) {
        super(value);
    }

    @Override
    public int compareTo(ComparableString x) {
        return value.compareTo(x.value);
    }
}
