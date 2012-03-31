package ru.spbau.kononenko.task1;

import java.io.Closeable;
import java.io.IOException;

/**
 * Base interface for message writer
 * @author Vasily Kononenko
 * @version %I%, %G%
 */
public interface MessageWriter extends Closeable {
    /**
     * releases the associated resource (for instance, closes the output file)
     * @throws IOException if some IO problem is encountered
     */
    void close() throws IOException;

    /**
     * writes the message
     * @param message the message to write
     * @throws IOException if some IO problem is encountered
     */
    void writeMessage(Message message) throws IOException;
}
