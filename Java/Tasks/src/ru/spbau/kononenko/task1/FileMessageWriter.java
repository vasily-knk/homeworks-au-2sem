package ru.spbau.kononenko.task1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Vasya
 * Date: 21.03.12
 * Time: 23:00
 * To change this template use File | Settings | File Templates.
 */
class FileMessageWriter implements MessageWriter {
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

    @Override
    public void close() throws IOException {
        writer.close();
    }
}
