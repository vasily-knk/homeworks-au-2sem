package ru.spbau.kononenko.drunkgame.common_algorithms.filters;

import ru.spbau.kononenko.drunkgame.common_algorithms.filters.FilterInterface;

import java.util.Collections;
import java.util.HashSet;

public class OneOfFilter<T> implements FilterInterface<T>  {
    HashSet<T> set = new HashSet<T>();

    public OneOfFilter(T... values) {
        Collections.addAll(set, values);
    }
    
    @Override
    public boolean accept(T o) {
        return (set.contains(o));
    }
}
