package ru.spbau.kononenko.task1;

import java.io.IOException;

/**
 * Message writer which joins pairs of messages
 * and sends them to the real writer specified
 * @author Vasily Kononenko
 * @version %I%, %G%
 */
public class CompressingMessageWriter implements MessageWriter {
    private static final int MAX_MESSAGES = 2;

    private int count = 0;
    private final Message joinedMessage = new Message();
    private final MessageWriter writer;

    /**
     * initializes this writer
     * @param writer the real writer to send messages to
     */
    public CompressingMessageWriter(MessageWriter writer) {
        this.writer = writer;
    }

    /**
     * joins the message to the pair and sends it to the real writer
     * once it is complete
     * @param message the message to join
     * @throws IOException if some IO problem is encountered
     */
    @Override
    public void writeMessage(Message message) throws IOException {
        joinedMessage.append(message);
        ++count;

        if (count == MAX_MESSAGES) {
            flush();
            count = 0;
        }
    }

    /**
     * flushes the message (even if not complete)
     * without closing the real writer
     * @throws IOException if some IO problem is encountered
     */
    @Override
    public void close() throws IOException {
        if (!joinedMessage.isEmpty())
            flush();
    }
    
    private void flush() throws IOException {
        writer.writeMessage(joinedMessage);
        joinedMessage.clear();
    }
}
