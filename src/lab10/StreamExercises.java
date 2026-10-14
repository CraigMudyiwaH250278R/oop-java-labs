package lab10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Exercises 10.2, 10.4 and 10.5 - Predicate/removeIf, streams, and groupingBy.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public final class StreamExercises {
    private StreamExercises() { }

    /** Exercise 10.2: Predicate + removeIf deletes all even numbers (mutates the given list). */
    public static void removeEvens(List<Integer> numbers) {
        Predicate<Integer> isEven = n -> n % 2 == 0;
        numbers.removeIf(isEven);
    }

    /** Exercise 10.4: names of learners ABOVE the class average, alphabetical. */
    public static List<String> aboveAverageNames(List<LearnerRecord> learners) {
        double average = learners.stream().mapToInt(LearnerRecord::mark).average().orElse(0);
        return learners.stream()
                .filter(l -> l.mark() > average)
                .map(LearnerRecord::name)
                .sorted()
                .toList();
    }

    /** Exercise 10.5 (load): reads learners.csv, skipping the header line. */
    public static List<LearnerRecord> loadLearners(Path csv) throws IOException {
        try (var lines = Files.lines(csv)) {
            return lines.skip(1)
                    .filter(l -> !l.isBlank())
                    .map(LearnerRecord::fromCsv)
                    .toList();
        }
    }

    /** Exercise 10.5 (group): band -> learners, with bands in A, B, C order. */
    public static Map<String, List<LearnerRecord>> groupByBand(List<LearnerRecord> learners) {
        return learners.stream()
                .collect(Collectors.groupingBy(LearnerRecord::band, TreeMap::new, Collectors.toList()));
    }

    /** Exercise 10.5 (write): one report file per band; returns the files created. */
    public static List<Path> writeBandReports(Map<String, List<LearnerRecord>> groups, Path outDir) throws IOException {
        Files.createDirectories(outDir);
        List<Path> written = new ArrayList<>();
        for (Map.Entry<String, List<LearnerRecord>> e : groups.entrySet()) {
            List<String> lines = new ArrayList<>();
            lines.add("Band " + e.getKey() + " report (" + e.getValue().size() + " learners)");
            lines.add("-".repeat(40));
            e.getValue().forEach(l -> lines.add(String.format("%-6s %-18s %3d", l.id(), l.name(), l.mark())));
            Path file = outDir.resolve("report_band_" + e.getKey() + ".txt");
            Files.write(file, lines);
            written.add(file);
        }
        return written;
    }
}
