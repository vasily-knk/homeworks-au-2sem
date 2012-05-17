package ru.spbau.kononenko.drunkgame.Drunks;

import ru.spbau.kononenko.drunkgame.Field.FieldObject;
import ru.spbau.kononenko.drunkgame.Field.FieldObjectProperty;

public class Bottle implements FieldObject {
    public static final FieldObjectProperty bottleProperty = FieldObjectProperty.createProperty();

    @Override
    public boolean getProperty(FieldObjectProperty property) {
        if (property == bottleProperty)
            return true;
        return false;
    }

    @Override
    public char getChar() {
        return 'B';
    }
}
