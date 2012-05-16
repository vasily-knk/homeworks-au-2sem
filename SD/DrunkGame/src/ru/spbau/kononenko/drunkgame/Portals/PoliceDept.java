package ru.spbau.kononenko.drunkgame.Portals;

import ru.spbau.kononenko.drunkgame.Searcher;
import ru.spbau.kononenko.drunkgame.Dynamic.DynamicControl;
import ru.spbau.kononenko.drunkgame.Field.Coord;
import ru.spbau.kononenko.drunkgame.Field.Field;
import ru.spbau.kononenko.drunkgame.Walking.Policeman;

import java.util.LinkedList;
import java.util.List;

public class PoliceDept extends Portal {
    List<Searcher> searchers = new LinkedList<Searcher>();
    boolean policemanIsOut = false;

    public PoliceDept(Field field, Coord coord, DynamicControl dynamicControl) {
        super(field, coord, dynamicControl);
    }

    public void addSearcher(Searcher searcher) {
        searchers.add(searcher);
    }

    @Override
    public void update() {
        for (Searcher searcher : searchers) {
            Coord res = searcher.search();
            if (res != null) {
                tryToSendPoliceman(res);
                break;
            }
        }
    }

    private void tryToSendPoliceman(Coord res) {
        if (canSpawn()) {
            spawn(new Policeman(field, coord, res));
        }
    }

    @Override
    public boolean isActive() {
        return !policemanIsOut;
    }
}
