package ru.spbau.kononenko.task1;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Vasya
 * Date: 21.03.12
 * Time: 22:37
 * To change this template use File | Settings | File Templates.
 */
interface MessageWriter extends Closeable {
    void writeMessage(Message message) throws IOException;
}
