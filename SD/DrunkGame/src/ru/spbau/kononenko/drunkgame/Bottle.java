package ru.spbau.kononenko.drunkgame;

import ru.spbau.kononenko.drunkgame.Field.FieldObject;
import ru.spbau.kononenko.drunkgame.Field.Property;

public class Bottle implements FieldObject {
    public static Property bottleProperty = Property.createProperty();

    @Override
    public boolean getProperty(Property property) {
        if (property == bottleProperty)
            return true;
        return false;
    }

    @Override
    public char getChar() {
        return 'B';
    }
}
