package ru.spbau.kononenko.task1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Message containing multiple lines of text
 * @author Vasily Kononenko
 * @version %I%, %G%
 */
public class Message {
    private final List<String> lines;

    /**
     * creates an empty message
     */
    public Message() {
        lines = new ArrayList<String>();
    }

    /**
     * appends a single line to this message
     * @param line the line to append
     */
    public void append(String line) {
        lines.add(line);
    }

    /**
     * appends all the lines from another message
     * @param other the message to append
     */
    public void append(Message other) {
        lines.addAll(other.lines);
    }

    /**
     * clears this message
     */
    public void clear() {
        lines.clear();
    }

    /**
     * returns unmodifiable list containing the lines from this message
     * @return the list of message lines
     */
    public List<String> getLines() {
        return Collections.unmodifiableList(lines);
    }

    /**
     * returns <code>true</code> if this message is empty
     * @return <code>true</code> if this message is empty
     */
    public boolean isEmpty() {
        return lines.isEmpty();
    }
}
