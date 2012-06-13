package ru.spbau.kononenko.drunkgame.drunks;

import ru.spbau.kononenko.drunkgame.common.field.objects.FieldObjectProperty;
import ru.spbau.kononenko.drunkgame.common.field.objects.StaticFieldObject;

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
