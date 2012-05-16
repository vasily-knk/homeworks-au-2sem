package ru.spbau.kononenko.drunkgame.Portals;

import ru.spbau.kononenko.drunkgame.BFS;
import ru.spbau.kononenko.drunkgame.DrunkSearcher;
import ru.spbau.kononenko.drunkgame.Dynamic.DynamicControl;
import ru.spbau.kononenko.drunkgame.Field.Coord;
import ru.spbau.kononenko.drunkgame.Field.Field;
import ru.spbau.kononenko.drunkgame.Static.Streetlight;
import ru.spbau.kononenko.drunkgame.Walking.Policeman;

import java.util.LinkedList;
import java.util.List;

public class PoliceDept extends Portal {
    List<DrunkSearcher> searchers = new LinkedList<DrunkSearcher>();
    boolean policemanIsOut = false;

    public PoliceDept(Field field, Coord coord, DynamicControl dynamicControl) {
        super(field, coord, dynamicControl);
    }

    public void addSearcher(DrunkSearcher searcher) {
        searchers.add(searcher);
    }

    @Override
    public void update() {
        for (DrunkSearcher searcher : searchers) {
            Coord res = searcher.searchForDrunk();
            if (res != null) {
                tryToSendPoliceman(res);
                break;
            }
        }
    }

    private void tryToSendPoliceman(Coord res) {
        if (canSpawn()) {
            spawn(new Policeman(field, coord));
        }
    }

    @Override
    public boolean isActive() {
        return !policemanIsOut;
    }

}
