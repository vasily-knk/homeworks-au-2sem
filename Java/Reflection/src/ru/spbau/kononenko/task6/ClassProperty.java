package ru.spbau.kononenko.task6;

public interface ClassProperty<T> {
    Object get(T obj);
    void set(T obj, Object value);
    Class<?> getType();
    boolean isWritable();
}
