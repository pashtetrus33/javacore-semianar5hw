package ru.geekbrains.javacore.seminar5hw.task2;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static ru.geekbrains.javacore.seminar5hw.task2.MainApp.FILENAME;
import static ru.geekbrains.javacore.seminar5hw.task2.States.*;

public class Map extends JPanel {

    private static final int DOT_PADDING = 5;
    private int cellHeight;
    private int cellWidth;
    private static final int FIELDSIZE = 3;
    private int fieldSizeY;
    private int fieldSizeX;
    private char[][] field;


    Map() {
        this.fieldSizeX = FIELDSIZE;
        this.fieldSizeY = FIELDSIZE;
    }


    void startNewGame() throws IOException {
        initMap();
        repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }

    /**
     * Метод отрисовки игрового поля
     *
     * @param g ссылка на абстрактный класс Graphics
     */
    private void render(Graphics g) {
        int panelHeight = getHeight();
        int panelWidth = getWidth();
        cellHeight = panelHeight / FIELDSIZE;
        cellWidth = panelWidth / FIELDSIZE;

        g.setColor(Color.BLACK);
        for (int h = 0; h < FIELDSIZE; h++) {
            int y = h * cellWidth;
            g.drawLine(0, y, panelWidth, y);
        }
        for (int w = 0; w < FIELDSIZE; w++) {
            int x = w * cellHeight;
            g.drawLine(x, 0, x, panelHeight);
        }

        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (field[y][x] == EMPTY_CELL.state) continue;

                if (field[y][x] == HUMAN_DOT.state) {
                    g.setColor(Color.BLUE);
                    g.fillOval(x * cellWidth + DOT_PADDING * 7,
                            y * cellHeight + DOT_PADDING * 3,
                            cellWidth - DOT_PADDING * 9,
                            cellHeight - DOT_PADDING * 9);

                } else if (field[y][x] == AI_DOT.state) {
                    g.setColor(new Color(0xff0000));
                    g.fillOval(x * cellWidth + DOT_PADDING * 7,
                            y * cellHeight + DOT_PADDING * 3,
                            cellWidth - DOT_PADDING * 9,
                            cellHeight - DOT_PADDING * 9);
                } else if (field[y][x] == REZERV.state) {
                    g.setColor(Color.YELLOW);
                    g.fillOval(x * cellWidth + DOT_PADDING * 7,
                            y * cellHeight + DOT_PADDING * 3,
                            cellWidth - DOT_PADDING * 9,
                            cellHeight - DOT_PADDING * 9);
                } else {
                    throw new RuntimeException("Unexpected value " + field[y][x] + " in cell: x=" + x + " y=" + y);
                }
            }
        }
    }

    /**
     * Метод чтения из файла и парсер игрового поля
     *
     * @param filename имя файла для чтения данных
     * @return возвращает целочисленный массив элементов со значениями от 0 до 3
     * @throws IOException исключение ввода вывода
     */

    public static int[] readGameFieldFromFile(String filename) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
        int valueFromFile = Integer.parseInt(bufferedReader.readLine());

        int[] result = new int[9];
        for (int i = 0; i < 9; i++) {
            result[i] = valueFromFile >> i * 2 & 3;  //Побитовый свдиг вправо и наложение маски на два начальных бита
        }
        for (int i : result) {
            System.out.println(i);
        }
        return result;
    }

    /**
     * Метод инициализации игрового поля данными из считанного файла
     *
     * @throws IOException исключение ввода вывода
     */
    private void initMap() throws IOException {
        //Считываем целочисленный массив данных из файла
        int[] fieldFromFile = readGameFieldFromFile(FILENAME);
        field = new char[fieldSizeY][fieldSizeX];

        int k = 0;
        for (int i = 0; i < fieldSizeY; i++)
            for (int j = 0; j < fieldSizeX; j++) {
                {
                    field[i][j] = (char) fieldFromFile[k++];
                }
            }
    }
}