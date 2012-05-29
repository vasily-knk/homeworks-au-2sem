package ru.spbau.kononenko.task3;

import java.util.List;

public class NumNode implements Node, Block {
    private int val;

    public NumNode(int val) {
        this.val = val;
    }

    @Override
    public Node tryToSplit(List<Block> list, int index, Stage stage) {
        if (list.size() == 1 && stage == Stage.EVALUATION)
            return this;
        return null;
    }
}
