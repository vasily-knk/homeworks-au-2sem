package ru.spbau.kononenko.drunkgame.Walking;

import ru.spbau.kononenko.drunkgame.Field.Coord;
import ru.spbau.kononenko.drunkgame.Field.Field;
import ru.spbau.kononenko.drunkgame.Field.Property;

public class Policeman extends Walking {
    public Policeman(Field field, Coord coord) {
        super(field, coord);
    }

    @Override
    public void update() {

    }

    @Override
    public boolean isActive() {
        return false;
    }

    @Override
    public boolean getProperty(Property property) {
        return false;
    }

    @Override
    public char getChar() {
        return '!';
    }
}
