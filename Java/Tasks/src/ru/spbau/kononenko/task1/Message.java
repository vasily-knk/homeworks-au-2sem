package ru.spbau.kononenko.task1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 *
 */
class Message {
    private final List<String> lines;

    public Message() {
        lines = new ArrayList<String>();
    }

    /**
     * Appends a single line to the message
     * @param line the line to append
     */
    public void append(String line) {
        lines.add(line);
    }

    /**
     * Appends another message
     * @param other the message to append
     */
    public void append(Message other) {
        lines.addAll(other.lines);
    }

    /**
     * Clears the message
     */
    public void clear() {
        lines.clear();
    }

    /**
     * Returns list of message lines
     * @return the lines list
     */
    public List<String> getLines() {
        return Collections.unmodifiableList(lines);
    }
}
