package ru.geekbrains.javacore.seminair5hw.task1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * Написать функцию, создающую резервную копию всех файлов в директории(без поддиректорий) во вновь созданную папку ./backup
 */

public class DirectoryBackup {

    /**
     * Метод создания резервной копии директории или файла
     * @param source директория - источник для копирования
     * @param destination директория - назначение для копирования
     * @throws IOException исключение ввода вывода
     */
    static void start(String source, String destination) throws IOException {

        Path input = Paths.get(source);
        if (!Files.exists(input)) {
            throw new IOException("Directory " + source + " not found");
        }

        File[] files = new File(source).listFiles();

        if (!Files.exists(Paths.get(destination))) {
            Files.createDirectory(Paths.get(destination));
        }

        if (Files.isRegularFile(input)) {
            Files.copy(Paths.get(source), Paths.get(destination + "/" + input.getFileName()), REPLACE_EXISTING);
        }
        if (files == null) {
            return;
        }
        for (File f : files) {
            if (f.isFile()) {
                Path path = Paths.get(f.getPath());
                Files.copy(path, Paths.get(destination + "/" + path.getFileName()), REPLACE_EXISTING);
            } else {
                start(f.getPath(), destination + "/" + f.getName());
            }
        }
    }
}
