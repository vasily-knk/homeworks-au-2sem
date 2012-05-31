package ru.spbau.kononenko.task6;

public interface Property {
    String get();
    void set(String value);
    boolean isReadonly();
    String getName();
}
