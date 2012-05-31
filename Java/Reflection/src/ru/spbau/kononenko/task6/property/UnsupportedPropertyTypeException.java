package ru.spbau.kononenko.task6.property;

public class UnsupportedPropertyTypeException extends Exception {
    public UnsupportedPropertyTypeException(Class<?> type) {
        super("Unsupported property type: " + type.getName());
    }
}
