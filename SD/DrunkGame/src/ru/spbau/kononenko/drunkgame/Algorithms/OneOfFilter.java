package ru.spbau.kononenko.drunkgame.Algorithms;

import ru.spbau.kononenko.drunkgame.Algorithms.FilterInterface;

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
