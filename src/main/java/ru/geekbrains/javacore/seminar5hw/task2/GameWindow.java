package ru.geekbrains.javacore.seminar5hw.task2;

import javax.swing.*;
import java.io.IOException;

public class GameWindow extends JFrame {
    private static final int WINDOW_HEIGHT = 555;
    private static final int WINDOW_WIDTH = 507;
    private static final int WINDOW_POSX = 800;
    private static final int WINDOW_POSY = 300;


    Map map;

    public GameWindow() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(WINDOW_POSX, WINDOW_POSY);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("TicTacToe");
        setResizable(false);

        map = new Map();
        add(map);
        setVisible(true);
    }

    void startNewGame() throws IOException {
        map.startNewGame();

    }
}