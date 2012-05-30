package ru.spbau.kononenko.task6;

import java.io.*;
import java.util.Properties;

public class ReflectionDeSerializer implements ReflectionDeSerializerInterface {
    @Override
    public <T> T deserialize(String filename, Class<T> clazz) throws IOException {
        Properties properties = new Properties();
        Reader r = new FileReader(filename);
        properties.load(r);
        for ()
    }
}
