package ru.spbau.kononenko.task1;

import java.io.IOException;

/**
 * Message writer which prints messages to the console in the following format:
 * <p />
 * <pre> </pre></pre>Message 1
 * 1.1. line 1
 * 1.2. line 2
 * ...</pre>
 * @author Vasily Kononenko
 * @version %I%, %G%
 */
public class ConsoleMessageWriter implements MessageWriter {
    private int count;

    /**
     * initializes this writer
     */
    public ConsoleMessageWriter() {
        count = 1;
    }

    @Override
    public void writeMessage(Message message) {
        System.out.println("Message " + count);

        int lineNo = 1;
        for (String line : message.getLines()) {
            System.out.println("" + count + "." + lineNo + ". " + line);
            ++lineNo;
        }

        ++count;
    }

    @Override
    public void close() throws IOException {

    }
}
