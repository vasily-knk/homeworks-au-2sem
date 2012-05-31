package ru.spbau.kononenko.task6.manager;

import ru.spbau.kononenko.task6.property.*;
import ru.spbau.kononenko.task6.property.UnexpectedException;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Properties manager used to manipulate properties with get/set methods.
 * @author Vasily Kononenko
 * @version %I%, %G%
 */
public class GetSetPropertiesManager implements PropertiesManager {
    @Override
    public <T> Property getProperty(T t, String name) throws UnsupportedPropertyTypeException,
            PropertyNotFoundException {
        return new GetSetProperty(t, name);
    }

    @Override
    public <T> List<Property> getAllProperties(T t) {
        List<Property> list = new ArrayList<Property>();
        for (Method m : t.getClass().getMethods()) {
            if(m.getName().length() < 4)
                continue;
            
            if(m.getName().substring(0, 3).equals("get")) {
                String newName = m.getName().substring(3, 4).toLowerCase()
                               + m.getName().substring(4);
                
                try {

                    list.add(new GetSetProperty(t, newName));
                } catch (PropertyNotFoundException e) {
                    // This should NEVER happen!
                    throw new UnexpectedException(e);
                } catch (UnsupportedPropertyTypeException e) {
                    // Unsupported type: well, just don't add it.
                    int i = 0;
                }
            }
        }
        return list;
    }
}
