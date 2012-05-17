package ru.spbau.kononenko.drunkgame;

public interface FilterInterface<T> {
    boolean accept(T t);
}
