package ru.spbau.kononenko.task1;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Vasya
 * Date: 21.03.12
 * Time: 23:33
 * To change this template use File | Settings | File Templates.
 */
class CompressingMessageWriter implements MessageWriter {
    private static final int MAX_MESSAGES = 2;

    private int count = 0;
    private final Message message = new Message();
    private final MessageWriter writer;

    public CompressingMessageWriter(MessageWriter writer) {
        this.writer = writer;
    }
    
    @Override
    public void writeMessage(Message new_message) throws IOException {
        message.append(new_message);
        ++count;

        if (count == MAX_MESSAGES) {
            flush();
            count = 0;
        }
    }

    @Override
    public void close() throws IOException {
        flush();
    }
    
    private void flush() throws IOException {
        writer.writeMessage(message);
        message.clear();
    }
}
