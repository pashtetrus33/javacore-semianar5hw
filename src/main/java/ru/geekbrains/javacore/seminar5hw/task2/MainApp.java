package ru.geekbrains.javacore.seminar5hw.task2;

import java.io.*;
import java.util.Random;

/**
 * 2. Предположить, что числа в исходном массиве из 9 элементов имеют диапазон[0, 3], и представляют собой, например,
 * состояния ячеек поля для игры в крестики-нолики, где 0 – это пустое поле, 1 – это поле с крестиком, 2 – это поле с ноликом, 3 – резервное значение.
 * Такое предположение позволит хранить в одном числе типа int всё поле 3х3. Записать в файл 9 значений так, чтобы они заняли три байта.
 * 3 (*) - В продолжение 2 дописать "разворачивание" поля игры крестики-нолики из сохраненного в файле состояния (распарсить файл,
 * в зависимости от значений (0-3) нарисовать поле со значками 'х' 'о' '.')
 */
public class MainApp {
    public static final String FILENAME = "output.txt";
    public static final int FIELDSIZE = 9;

    public static void main(String[] args) {
        try {
            writeGameFieldToFile(arrayCreate(FIELDSIZE), FILENAME, FIELDSIZE);
            new GameWindow().startNewGame();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод записи игрового поля в виде int в указанный файл
     *
     * @param inputArray входной целочисленный массив
     * @param filename   имя файла для записи
     * @param fieldSize  размер игрового поля
     * @throws IOException исключение ввода вывода
     */
    public static void writeGameFieldToFile(int[] inputArray, String filename, int fieldSize) throws IOException {
        FileWriter fileWriter = new FileWriter(filename);

        int result = 0;
        for (int i = 0; i < fieldSize; i++) {
            result += inputArray[i] << i * 2;  // Побитовый сдвиг влево
        }
        fileWriter.append(String.valueOf(result));  // Запись строкового значения целого числа в файл
        fileWriter.flush();
    }

    /**
     * Метод рандомного создания массива из значений перечисления
     *
     * @param size размер генерируемого массива
     * @return
     */
    public static int[] arrayCreate(int size) {
        int[] arrayOfStates = new int[size];
        Random random = new Random();
        States[] states = States.values();
        //Заполнение случайными значениями из перечислений
        for (int i = 0; i < arrayOfStates.length; i++) {
            arrayOfStates[i] = states[random.nextInt(4)].getValue();
            System.out.println("Random state is: " + arrayOfStates[i]);
        }

        return arrayOfStates;
    }
}
