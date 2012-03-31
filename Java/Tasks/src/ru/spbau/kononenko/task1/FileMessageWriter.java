package ru.spbau.kononenko.task1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Message writer which writes messages to a text file in the following format:
 * <p />
 * <pre> Number of lines in message 1
 * Line 1
 * Line 2
 * ...
 * Number of lines in message 2
 * ...</pre>
 * @author Vasily Kononenko
 * @version %I%, %G%
 */
public class FileMessageWriter implements MessageWriter {
    private final BufferedWriter writer;

    public FileMessageWriter(String path) throws IOException {
        this.writer = new BufferedWriter(new FileWriter(path));
    }

    @Override
    public void writeMessage(Message message) throws IOException {
        writer.write("" + message.getLines().size());
        writer.newLine();

        for (String line : message.getLines()) {
            writer.write(line);
            writer.newLine();
        }
    }

    /**
     * closes the output file
     * @throws IOException if some IO problem is encountered
     */
    @Override
    public void close() throws IOException {
        writer.close();
    }
}
