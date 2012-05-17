package ru.spbau.kononenko.drunkgame.Logic;

import ru.spbau.kononenko.drunkgame.Field.Coord;
import ru.spbau.kononenko.drunkgame.Field.Field;

public interface SelfAware {
    Coord getCoord();
    Field getField();
}
