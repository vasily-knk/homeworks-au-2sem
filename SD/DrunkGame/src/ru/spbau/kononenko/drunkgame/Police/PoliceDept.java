package ru.spbau.kononenko.drunkgame.Police;

import ru.spbau.kononenko.drunkgame.Logic.DynamicControl;
import ru.spbau.kononenko.drunkgame.Logic.Portal;
import ru.spbau.kononenko.drunkgame.Logic.ReportInterface;
import ru.spbau.kononenko.drunkgame.Field.Coord;
import ru.spbau.kononenko.drunkgame.Field.Field;

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
            spawn(new Policeman(field, coord, target, new ReportInterface() {
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
