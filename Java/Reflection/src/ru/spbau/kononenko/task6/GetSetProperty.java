package ru.spbau.kononenko.task6;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class GetSetProperty<T> implements ClassProperty<T> {
    final Class<T> clazz;
    final String name;
    
    Method getter = null;
    Method setter = null;

    public GetSetProperty(Class<T> clazz, String name) {
        this.clazz = clazz;
        this.name = name;
        
        if (name.length() < 1 || name.charAt(0) < 'a' || name.charAt(0) > 'z')
            throw new PropertyNotFoundException(clazz, name);

        final String fixedName = name.substring(0, 1).toUpperCase() + name.substring(1);
        
        final String getterName = "get" + fixedName;
        try {
            getter = clazz.getMethod(getterName);
        } catch (NoSuchMethodException e) {
            throw new PropertyNotFoundException(clazz, name);
        }
        
        if (!Modifier.isPublic(getter.getModifiers()))
            throw new PropertyNotFoundException(clazz, name);

        final String setterName = "set" + fixedName;
        try {
            setter = clazz.getMethod(setterName, getType());
        } catch (NoSuchMethodException e) {
            // setter not found, so the property is read-only
        }

        if (!Modifier.isPublic(setter.getModifiers()))
            setter = null;
    }

    @Override
    public Object get(T obj) {
        try {
            return getter.invoke(obj);
        } catch (IllegalAccessException e) {
            throw new UnexpectedException(e);
        } catch (InvocationTargetException e) {
            throw new UnexpectedException(e);
        }
    }

    @Override
    public void set(T obj, Object value) {
        if (!isWritable())
            throw new PropertyReadOnlyException(clazz, name);
        try {
            setter.invoke(obj, value);
        } catch (IllegalAccessException e) {
            throw new UnexpectedException(e);
        } catch (InvocationTargetException e) {
            throw new UnexpectedException(e);
        }
    }

    @Override
    public Class<?> getType() {
        return getter.getReturnType();
    }

    @Override
    public boolean isWritable() {
        return (setter != null);
    }

}
