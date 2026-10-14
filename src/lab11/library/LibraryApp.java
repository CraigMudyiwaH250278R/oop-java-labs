package lab11.library;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.time.Clock;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Console menu for the Library Management System. Loads data on start and saves on exit.
 *
 * <p>The UI is kept separate from the {@link Library} rules (single responsibility): this class only
 * reads input, calls the service and prints results. Input and output streams are injected so the
 * menu can be driven by a script in {@link LibraryDemo} and in tests.
 *
 * <p>Run: {@code run.bat library} (data directory {@code library-data/}), or pass another directory.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public class LibraryApp {
    private static final String MENU =
            "\n[1] Add item  [2] Register member  [3] Borrow  [4] Return  [5] Overdue  [6] Search title  [7] List items  [0] Save & exit";

    /** Signals that scripted input ran out; treated like choosing "exit". */
    private static final class EndOfInput extends RuntimeException {
        private static final long serialVersionUID = 1L;
    }

    private final Library library;
    private final Scanner in;
    private final PrintStream out;
    private final Path dataDir;
    private final boolean echoInput;

    public LibraryApp(Library library, Scanner in, PrintStream out, Path dataDir, boolean echoInput) {
        this.library = library;
        this.in = in;
        this.out = out;
        this.dataDir = dataDir;
        this.echoInput = echoInput;
    }

    public static void main(String[] args) throws IOException {
        Path dir = Path.of(args.length > 0 ? args[0] : "library-data");
        Library library = LibraryStore.load(dir, Clock.systemDefaultZone());
        System.out.println("Library Management System - loaded " + library.getItems().size() + " items, "
                + library.getMembers().size() + " members from " + dir);
        new LibraryApp(library, new Scanner(System.in), System.out, dir, false).run();
    }

    public void run() {
        try {
            boolean running = true;
            while (running) {
                out.println(MENU);
                String choice = ask("Choice");
                switch (choice) {
                    case "1" -> addItem();
                    case "2" -> registerMember();
                    case "3" -> borrow();
                    case "4" -> giveBack();
                    case "5" -> listOverdue();
                    case "6" -> search();
                    case "7" -> listItems();
                    case "0" -> running = false;
                    default -> out.println("Invalid choice: " + choice);
                }
            }
        } catch (EndOfInput e) {
            out.println("(end of input)");
        }
        save();
    }

    // ------------------------------------------------------------------ actions

    private void addItem() {
        String type = ask("Type (B = book, D = DVD)").toUpperCase();
        String id = ask("Item id");
        String title = ask("Title");
        try {
            switch (type) {
                case "B" -> library.addItem(new Book(id, title, ask("Author")));
                case "D" -> library.addItem(new Dvd(id, title, parseInt(ask("Runtime (minutes)"))));
                default -> {
                    out.println("Error: type must be B or D");
                    return;
                }
            }
            out.println("Added item " + id);
        } catch (IllegalArgumentException e) {
            out.println("Error: " + e.getMessage());
        }
    }

    private void registerMember() {
        String id = ask("Member id");
        String name = ask("Name");
        try {
            library.registerMember(new Member(id, name));
            out.println("Registered member " + id);
        } catch (IllegalArgumentException e) {
            out.println("Error: " + e.getMessage());
        }
    }

    private void borrow() {
        String memberId = ask("Member id");
        String itemId = ask("Item id");
        try {
            Loan loan = library.borrow(memberId, itemId);
            out.println("OK: " + loan);
        } catch (LibraryException | IllegalArgumentException e) {   // checked business errors + unknown ids
            out.println("Error: " + e.getMessage());
        }
    }

    private void giveBack() {
        String itemId = ask("Item id");
        try {
            Loan loan = library.giveBack(itemId);
            long late = loan.daysLate(library.today());
            out.println("Returned: " + loan + (late > 0 ? "  ** " + late + " day(s) late **" : ""));
        } catch (IllegalArgumentException | IllegalStateException e) {
            out.println("Error: " + e.getMessage());
        }
    }

    private void listOverdue() {
        List<Loan> overdue = library.overdueLoans();
        if (overdue.isEmpty()) {
            out.println("No overdue items.");
            return;
        }
        out.println("Overdue items (as of " + library.today() + "):");
        for (Loan l : overdue) {
            out.printf("  %-4s %-30s %-14s due %s (%d days overdue)%n", l.getItem().getId(), l.getItem().getTitle(),
                    l.getMember().getName(), l.getDueOn(), l.daysLate(library.today()));
        }
    }

    private void search() {
        String text = ask("Search text");
        List<LibraryItem> hits = library.searchByTitle(text);
        if (hits.isEmpty()) {
            out.println("No titles match '" + text + "'.");
        } else {
            hits.forEach(i -> out.println("  " + i));
        }
    }

    private void listItems() {
        if (library.getItems().isEmpty()) {
            out.println("No items yet.");
            return;
        }
        library.getItems().forEach(i -> out.println("  " + i));
    }

    // ------------------------------------------------------------------ helpers

    private void save() {
        try {
            LibraryStore.save(library, dataDir);
            out.println("Data saved to " + dataDir + ". Goodbye!");
        } catch (IOException e) {
            out.println("WARNING: could not save data: " + e.getMessage());
        }
    }

    private String ask(String label) {
        out.print(label + ": ");
        try {
            String answer = in.nextLine().trim();
            if (echoInput) out.println(answer);
            return answer;
        } catch (NoSuchElementException e) {
            throw new EndOfInput();
        }
    }

    /** Parses an int, turning bad input into the IllegalArgumentException the callers already handle. */
    private static int parseInt(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("'" + text + "' is not a whole number");
        }
    }
}
