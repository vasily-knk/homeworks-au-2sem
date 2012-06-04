package ru.spbau.kononenko.drunkgame.common_algorithms.filters;

import ru.spbau.kononenko.drunkgame.common_algorithms.filters.FilterInterface;

import java.util.HashSet;

public class OneOfFilter<T> implements FilterInterface {
    HashSet<T> set = new HashSet<T>();
    
    public void add(T object) {
        set.add(object);
    }
    
    @Override
    public boolean accept(Object o) {
        return (set.contains(o));
    }
}
