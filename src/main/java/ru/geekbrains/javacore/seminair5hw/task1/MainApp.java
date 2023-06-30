package ru.geekbrains.javacore.seminair5hw.task1;

import java.io.IOException;

/**
 * 1. Написать метод, создающий резервную копию всех файлов в директории во вновь созданную папку ./backup
 */

public class MainApp {
    public static void main(String[] args) {
        try {
            DirectoryBackup.start(".", "./backup");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
