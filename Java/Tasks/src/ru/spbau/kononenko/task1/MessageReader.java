package ru.spbau.kononenko.task1;

import java.io.Closeable;
import java.io.IOException;

/**
 * Base interface for message reader
 * @author Vasily Kononenko
 * @version %I%, %G%
 */
public interface MessageReader extends Closeable {
    /**
     * releases the associated resource (for instance, closes the input file)
     * @throws IOException if some IO problem is encountered
     */
    public void close() throws IOException;

    /**
     * reads the message
     * @return the message read or <code>null</code> if there are no messages left
     * @throws IOException if the message can't be read because of some IO problem
     * @throws IllegalMessageFormatException if the input isn't properly formatted
     */
    public Message readMessage() throws IOException, IllegalMessageFormatException;
}
