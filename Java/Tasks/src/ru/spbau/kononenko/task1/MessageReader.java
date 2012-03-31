package ru.spbau.kononenko.task1;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Vasya
 * Date: 31.03.12
 * Time: 2:41
 * To change this template use File | Settings | File Templates.
 */
interface MessageReader extends Closeable {
    public void close() throws IOException;
    public Message readMessage() throws IOException, IllegalMessageFormatException;
}
