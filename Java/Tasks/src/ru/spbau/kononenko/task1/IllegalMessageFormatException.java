package ru.spbau.kononenko.task1;

/**
 * Created by IntelliJ IDEA.
 * User: Vasya
 * Date: 21.03.12
 * Time: 22:33
 * To change this template use File | Settings | File Templates.
 */
class IllegalMessageFormatException extends Exception {
    public IllegalMessageFormatException(String str) {
        super (str);
    }
}
