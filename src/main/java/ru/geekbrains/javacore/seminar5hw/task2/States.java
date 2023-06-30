package ru.geekbrains.javacore.seminar5hw.task2;

public enum States {
    EMPTY_CELL(0), HUMAN_DOT(1), AI_DOT(2), REZERV(3);
    int state;

    States(int state) {
        this.state = state;
    }

    public int getValue() {
        return state;
    }
}
