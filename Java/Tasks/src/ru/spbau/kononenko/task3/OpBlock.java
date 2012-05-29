package ru.spbau.kononenko.task3;

import java.util.List;

public class OpBlock implements Block {
    private OpType type = null;
    
    public OpBlock(OpType type) {
        this.type = type;
    }

    @Override
    public Node tryToSplit(List<Block> list, int index, Stage stage) {
        if (type.getStage() == stage)
            return split(list, index);
        return null;
    }
    
    @Override
    public String toString() {
        return "Operation block: " + type.toString();
    }

    private Node split(List<Block> list, int index) {
        Node left = Main.splitBlockList(list.subList(0, index));
        Node right = Main.splitBlockList(list.subList(index + 1, list.size()));
        return new OpNode(type, left, right);
    }

}
