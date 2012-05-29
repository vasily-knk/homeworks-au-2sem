package ru.spbau.kononenko.task3;

import java.util.List;

/**
 * This class represents a single
 * @author Vasily Kononenko
 * @version %I%, %G%
*/
public interface Block {
    Node tryToSplit(List<Block> list, int index, Stage stage);

    public enum Stage {
        BRACKETS,
        ADDITIVE,
        MULTIPLICATIVE,
        EVALUATION
    }
}
