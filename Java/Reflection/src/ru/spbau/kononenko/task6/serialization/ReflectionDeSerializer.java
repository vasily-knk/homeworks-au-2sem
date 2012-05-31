package ru.spbau.kononenko.task6.serialization;

import ru.spbau.kononenko.task6.manager.PropertiesManager;
import ru.spbau.kononenko.task6.property.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Class to deserialize properties using java Properties class
 * @author Vasily Kononenko
 * @version %I%, %G%
*/
public class ReflectionDeSerializer implements ReflectionDeSerializerInterface {
    private PropertiesManager manager;

    /**
     *
     * @param manager the class properties manager used
     */
    public ReflectionDeSerializer(PropertiesManager manager) {
        this.manager = manager;
    }

    @Override
    public <T> T deserialize(String filename, Class<T> clazz) throws IOException,
            InstantiationException,
            IllegalAccessException,
            UnsupportedPropertyTypeException,
            PropertyNotFoundException {
        
        Properties src = new Properties();
        src.load(new FileInputStream(filename));

        T t = clazz.newInstance();

        for (String name : src.stringPropertyNames()) {
            Property p = manager.getProperty(t, name);
            if (p.isReadonly())
                continue;
            try {
                p.set(src.getProperty(name));
            } catch (PropertyReadOnlyException e) {
                // Should never happen
                throw new UnexpectedException(e);
            }
        }

        return t;
    }
}
