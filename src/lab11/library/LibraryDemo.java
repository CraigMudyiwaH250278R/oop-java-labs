package lab11.library;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Scripted, reproducible demonstration of the whole mini-project using the REAL menu.
 *
 * <p>SESSION 1 (1 October 2026): add items and members, borrow, trigger both custom exceptions, search, save.
 * <p>SESSION 2 (25 October 2026): a brand-new {@link Library} reloads the CSV files (proving persistence),
 * lists overdue items (proving the stored dates and the DVD/book loan periods), returns an item late, borrows again.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public class LibraryDemo {
    private static Clock on(int year, int month, int day) {
        return Clock.fixed(LocalDate.of(year, month, day).atStartOfDay(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
    }

    public static void main(String[] args) throws IOException {
        Path dir = Path.of("output", "library-demo");
        if (Files.exists(dir)) {                                   // start every demo from a clean slate
            try (Stream<Path> files = Files.walk(dir)) {
                for (Path p : files.sorted(Comparator.reverseOrder()).toList()) Files.delete(p);
            }
        }

        System.out.println("################ SESSION 1 - 1 Oct 2026 (empty library) ################");
        String session1 = String.join("\n",
                "1", "B", "B001", "Clean Code", "Robert C. Martin",
                "1", "B", "B002", "Effective Java", "Joshua Bloch",
                "1", "B", "B003", "Design Patterns, Elements of Reusable Software", "Gamma et al.",
                "1", "D", "D001", "Inception", "148",
                "1", "B", "B001", "Duplicate Id Book", "Nobody",          // rejected: duplicate id
                "2", "M001", "Tariro Moyo",
                "2", "M002", "Farai Ncube",
                "3", "M001", "B001",                                      // book: 21-day loan
                "3", "M001", "D001",                                      // DVD: 7-day loan
                "3", "M002", "B001",                                      // ItemNotAvailableException
                "3", "M001", "B002",                                      // 3rd loan - allowed
                "3", "M001", "B003",                                      // MemberLimitExceededException
                "3", "M009", "B003",                                      // unknown member
                "6", "design",                                            // stream search, case-insensitive
                "7",
                "0") + "\n";
        Library first = LibraryStore.load(dir, on(2026, 10, 1));
        new LibraryApp(first, new Scanner(session1), System.out, dir, true).run();

        System.out.println("\n################ SESSION 2 - 25 Oct 2026 (reloaded from CSV files) ################");
        String session2 = String.join("\n",
                "5",                                                      // overdue list
                "4", "D001",                                              // return a late DVD
                "4", "D001",                                              // not on loan any more
                "3", "M001", "B003",                                      // now allowed (back to 2 loans)
                "5",
                "0") + "\n";
        Library second = LibraryStore.load(dir, on(2026, 10, 25));
        System.out.println("Reloaded " + second.getItems().size() + " items, " + second.getMembers().size()
                + " members, " + second.getLoanHistory().size() + " loan records.");
        new LibraryApp(second, new Scanner(session2), System.out, dir, true).run();

        System.out.println("\n--- " + dir.resolve("loans.csv") + " ---");
        Files.readAllLines(dir.resolve("loans.csv")).forEach(System.out::println);
    }
}
