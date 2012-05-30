package ru.spbau.kononenko.drunkgame.Drunks;

import ru.spbau.kononenko.drunkgame.Field.FieldObject;
import ru.spbau.kononenko.drunkgame.Field.FieldObjectProperty;
import ru.spbau.kononenko.drunkgame.Field.StaticFieldObject;

public class Bottle extends StaticFieldObject {
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
