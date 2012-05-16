package ru.spbau.kononenko.drunkgame;

import ru.spbau.kononenko.drunkgame.Field.Coord;

import java.util.List;

public interface PathFinder {
    List<Coord> getPath(Coord src, Coord dst);
}
