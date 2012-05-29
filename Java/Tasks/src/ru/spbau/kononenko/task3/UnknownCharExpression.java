package ru.spbau.kononenko.task3;

public class UnknownCharExpression extends RuntimeException {
    public UnknownCharExpression(char c) {
        super("Unknown char: " + c);
    }
}
