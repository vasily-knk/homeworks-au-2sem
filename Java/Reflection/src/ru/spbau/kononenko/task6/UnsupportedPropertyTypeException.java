package ru.spbau.kononenko.task6;

public class UnsupportedPropertyTypeException extends Exception {
    public UnsupportedPropertyTypeException(Class<?> type) {
        super("Unsupported property type: " + type.getName());
    }
}
