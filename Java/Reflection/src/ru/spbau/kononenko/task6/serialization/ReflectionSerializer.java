package ru.spbau.kononenko.task6.serialization;

import ru.spbau.kononenko.task6.manager.PropertiesManager;
import ru.spbau.kononenko.task6.property.Property;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * Class to serialize properties using java Properties class
 * @author Vasily Kononenko
 * @version %I%, %G%
*/
public class ReflectionSerializer implements ReflectionSerializerInterface {
    private PropertiesManager manager;

    /**
     *
     * @param manager the class properties manager used
     */
    public ReflectionSerializer(PropertiesManager manager) {
        this.manager = manager;
    }

    @Override
    public <T> void serialize(T t, String filename) throws IOException {
        Properties dst = new Properties();
        List<Property> list = manager.getAllProperties(t);
        for (Property p : list) {
            dst.setProperty(p.getName(), p.get());
        }
        dst.store(new FileOutputStream(filename), "Serialization");
    }
}
