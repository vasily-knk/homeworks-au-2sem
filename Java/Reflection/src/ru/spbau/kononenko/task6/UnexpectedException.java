package ru.spbau.kononenko.task6;

public class UnexpectedException extends RuntimeException {
    private final Exception innerException;

    public UnexpectedException(Exception e) {
        super("Unexpected exception: " + e.getMessage());
        innerException = e;
    }

    public Exception getInnerException() {
        return innerException;
    }

}
