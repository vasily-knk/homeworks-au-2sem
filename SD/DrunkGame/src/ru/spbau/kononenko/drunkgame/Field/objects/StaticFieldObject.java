package ru.spbau.kononenko.drunkgame.field.objects;

import ru.spbau.kononenko.drunkgame.field.field_itself.Coord;

public abstract class StaticFieldObject implements FieldObject{

    @Override
    public void onRemove(Coord coord) {
    }

    @Override
    public void onPlace(Coord coord) {
    }
}
