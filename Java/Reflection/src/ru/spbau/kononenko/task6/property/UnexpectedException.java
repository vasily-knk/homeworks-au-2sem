package ru.spbau.kononenko.task6.property;

/**
 * Exception thrown to wrap some unexpected exception
 * @author Vasily Kononenko
 * @version %I%, %G%
*/
public class UnexpectedException extends RuntimeException {
    private final Exception innerException;

    /**
     * Constructor
     * @param e the inner exception
     */
    public UnexpectedException(Exception e) {
        super("Unexpected exception: " + e.getMessage());
        innerException = e;
    }

    /**
     * Gets the inner exception.
     * @return the inner exception
     */
    public Exception getInnerException() {
        return innerException;
    }

}
