package ru.spbau.kononenko.task1;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Vasya
 * Date: 21.03.12
 * Time: 22:39
 * To change this template use File | Settings | File Templates.
 */
class ConsoleMessageWriter implements MessageWriter {
    private int count = 1;

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
