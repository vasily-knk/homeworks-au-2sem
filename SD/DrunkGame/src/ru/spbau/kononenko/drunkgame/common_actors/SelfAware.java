package ru.spbau.kononenko.drunkgame.common_actors;

import ru.spbau.kononenko.drunkgame.field.field_itself.Coord;
import ru.spbau.kononenko.drunkgame.field.field_itself.Field;

public interface SelfAware {
    Coord getCoord();
    Field getField();
}
