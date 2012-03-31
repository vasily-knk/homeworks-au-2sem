package ru.spbau.kononenko.task1;

/**
 * The exception thrown if an input file can't be parsed
 * @author Vasily Kononenko
 * @version %I%, %G%
 */
public class IllegalMessageFormatException extends Exception {
    /**
     * constructor
     * @param str exception string
     */
    public IllegalMessageFormatException(String str) {
        super (str);
    }
}
