package ru.spbau.kononenko.task3;

public class NotAnOpCharException extends RuntimeException {
    public NotAnOpCharException(char c) {
        super("Not an operation char: " + c);
    }
}
