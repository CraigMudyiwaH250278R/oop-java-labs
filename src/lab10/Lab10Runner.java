package lab10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Lab 10 runner. Run from the repository root so {@code data/} and {@code output/} resolve.
 * Uses scripted input by default; pass {@code -i} to type the five lines yourself.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public class Lab10Runner {
    private static final Path DATA = Path.of("data");
    private static final Path OUT = Path.of("output");

    public static void main(String[] args) throws IOException {
        boolean interactive = args.length > 0 && args[0].equals("-i");
        Files.createDirectories(OUT);

        System.out.println("=== Ex 10.1 Save five lines and read them back ===");
        Scanner in = interactive
                ? new Scanner(System.in)
                : new Scanner("Line one\nLine two\nLine three\nLine four\nLine five\n");
        if (interactive) System.out.println("Type five lines:");
        Path notes = OUT.resolve("notes.txt");
        FileExercises.saveLines(in, 5, notes);
        FileExercises.readLines(notes).forEach(l -> System.out.println("  read: " + l));

        System.out.println("\n=== Ex 10.2 Predicate + removeIf (remove evens) ===");
        List<Integer> nums = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        System.out.println("before: " + nums);
        StreamExercises.removeEvens(nums);
        System.out.println("after : " + nums);

        System.out.println("\n=== Ex 10.3 Lines, words, characters of a text file ===");
        Path sample = DATA.resolve("sample.txt");
        long[] c = FileExercises.countLinesWordsChars(sample);
        System.out.printf("%s -> lines=%d, words=%d, chars=%d%n", sample, c[0], c[1], c[2]);

        System.out.println("\n=== Ex 10.4 Streams: learners above class average ===");
        List<LearnerRecord> learners = StreamExercises.loadLearners(DATA.resolve("learners.csv"));
        double avg = learners.stream().mapToInt(LearnerRecord::mark).average().orElse(0);
        System.out.printf("Class average = %.1f%n", avg);
        StreamExercises.aboveAverageNames(learners).forEach(n -> System.out.println("  " + n));

        System.out.println("\n=== Ex 10.5 groupingBy band, one report file per band ===");
        Map<String, List<LearnerRecord>> groups = StreamExercises.groupByBand(learners);
        groups.forEach((band, list) -> System.out.println("Band " + band + ": " + list.stream().map(LearnerRecord::name).toList()));
        StreamExercises.writeBandReports(groups, OUT).forEach(p -> System.out.println("Wrote " + p));
    }
}
