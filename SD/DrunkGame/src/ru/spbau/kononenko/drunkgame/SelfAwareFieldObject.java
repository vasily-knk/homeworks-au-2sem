package ru.spbau.kononenko.drunkgame;

import ru.spbau.kononenko.drunkgame.Field.Coord;
import ru.spbau.kononenko.drunkgame.Field.Field;
import ru.spbau.kononenko.drunkgame.Field.FieldObject;

public interface SelfAwareFieldObject extends FieldObject {
    Coord getCoord();
    Field getField();
}
