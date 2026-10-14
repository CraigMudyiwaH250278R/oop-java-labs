package lab10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

/**
 * Exercises 10.1 and 10.3 - Text file I/O with java.nio.file and try-with-resources.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public final class FileExercises {
    private FileExercises() { }

    /** Reads {@code count} lines from the scanner and saves them to {@code file}. */
    public static void saveLines(Scanner in, int count, Path file) throws IOException {
        List<String> lines = new java.util.ArrayList<>();
        for (int i = 1; i <= count; i++) {
            lines.add(in.nextLine());
        }
        Files.write(file, lines);                       // creates/overwrites; closes the file for us
    }

    public static List<String> readLines(Path file) throws IOException {
        return Files.readAllLines(file);
    }

    /** @return {lines, words, characters} of the file. */
    public static long[] countLinesWordsChars(Path file) throws IOException {
        long lines = 0, words = 0, chars = 0;
        for (String line : Files.readAllLines(file)) {
            lines++;
            chars += line.length() + 1;                 // +1 for the newline character
            String trimmed = line.trim();
            if (!trimmed.isEmpty()) {
                words += trimmed.split("\\s+").length;
            }
        }
        return new long[] { lines, words, chars };
    }
}
