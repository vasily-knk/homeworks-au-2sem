package ru.spbau.kononenko.drunkgame.Portals;

import ru.spbau.kononenko.drunkgame.Arrestable;
import ru.spbau.kononenko.drunkgame.PoliceReportInterface;
import ru.spbau.kononenko.drunkgame.ArrestableReporter;
import ru.spbau.kononenko.drunkgame.Dynamic.DynamicControl;
import ru.spbau.kononenko.drunkgame.Field.Coord;
import ru.spbau.kononenko.drunkgame.Field.Field;
import ru.spbau.kononenko.drunkgame.Walking.Policeman;

import java.util.LinkedList;
import java.util.List;

public class PoliceDept extends Portal {
    List<ArrestableReporter> reporters = new LinkedList<ArrestableReporter>();
    boolean policemanIsOut = false;

    public PoliceDept(Field field, Coord coord, DynamicControl dynamicControl) {
        super(field, coord, dynamicControl);
    }

    public void addSearcher(ArrestableReporter reporter) {
        reporters.add(reporter);
    }

    @Override
    public void update() {
        for (ArrestableReporter reporter : reporters) {
            Arrestable res = reporter.search();
            if (res != null) {
                tryToSendPoliceman(res);
                break;
            }
        }
    }

    private void tryToSendPoliceman(Arrestable target) {
        policemanIsOut = true;
        if (canSpawn()) {
            spawn(new Policeman(field, coord, target, new PoliceReportInterface() {
                @Override
                public void report() {
                    policemanIsOut = false;
                }
            }));
        }
    }

    @Override
    public boolean isActive() {
        return !policemanIsOut;
    }

}
