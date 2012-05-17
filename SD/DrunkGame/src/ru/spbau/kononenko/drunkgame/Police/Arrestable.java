package ru.spbau.kononenko.drunkgame.Police;

import ru.spbau.kononenko.drunkgame.Logic.SelfAware;
import ru.spbau.kononenko.drunkgame.Field.FieldObject;

public interface Arrestable extends FieldObject, SelfAware {
    void arrest(Policeman policeman);
}
