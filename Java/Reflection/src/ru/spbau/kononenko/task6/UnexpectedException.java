package ru.spbau.kononenko.task6;

public class UnexpectedException extends RuntimeException {
    public UnexpectedException(Exception e) {
        super("Unexpected exception: " + e.getMessage());
    }
}
