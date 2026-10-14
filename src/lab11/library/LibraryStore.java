package lab11.library;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Clock;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * CSV persistence (Lab 10 skills): three files in one directory.
 * <pre>
 *   items.csv    type,id,title,detail
 *   members.csv  id,name
 *   loans.csv    itemId,memberId,borrowedOn,dueOn,returnedOn      (returnedOn empty = still on loan)
 * </pre>
 * Whether an item is available is NOT stored: it is derived from the active loans on load, so
 * the files cannot contradict themselves. Each file is written to a temp file and then moved into place,
 * so a crash mid-save cannot leave a half-written file.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public final class LibraryStore {
    private static final String ITEMS = "items.csv";
    private static final String MEMBERS = "members.csv";
    private static final String LOANS = "loans.csv";

    private LibraryStore() { }

    public static void save(Library library, Path dir) throws IOException {
        Files.createDirectories(dir);

        List<String> itemLines = new ArrayList<>(List.of("type,id,title,detail"));
        for (LibraryItem i : library.getItems()) {
            itemLines.add(Csv.line(i.type(), i.getId(), i.getTitle(), i.detail()));
        }
        List<String> memberLines = new ArrayList<>(List.of("id,name"));
        for (Member m : library.getMembers()) {
            memberLines.add(Csv.line(m.getId(), m.getName()));
        }
        List<String> loanLines = new ArrayList<>(List.of("itemId,memberId,borrowedOn,dueOn,returnedOn"));
        for (Loan l : library.getLoanHistory()) {
            loanLines.add(Csv.line(l.getItem().getId(), l.getMember().getId(), l.getBorrowedOn().toString(),
                    l.getDueOn().toString(), l.getReturnedOn() == null ? "" : l.getReturnedOn().toString()));
        }
        write(dir.resolve(ITEMS), itemLines);
        write(dir.resolve(MEMBERS), memberLines);
        write(dir.resolve(LOANS), loanLines);
    }

    /** Loads a library; a missing directory or file simply means "start empty". */
    public static Library load(Path dir, Clock clock) throws IOException {
        Library library = new Library(clock);
        for (List<String> f : read(dir.resolve(ITEMS), ITEMS)) {
            try {
                library.addItem(switch (f.get(0)) {
                    case "BOOK" -> new Book(f.get(1), f.get(2), f.get(3));
                    case "DVD" -> new Dvd(f.get(1), f.get(2), Integer.parseInt(f.get(3)));
                    default -> throw new IllegalArgumentException("unknown item type " + f.get(0));
                });
            } catch (RuntimeException e) {
                throw corrupt(ITEMS, f, e);
            }
        }
        for (List<String> f : read(dir.resolve(MEMBERS), MEMBERS)) {
            try {
                library.registerMember(new Member(f.get(0), f.get(1)));
            } catch (RuntimeException e) {
                throw corrupt(MEMBERS, f, e);
            }
        }
        for (List<String> f : read(dir.resolve(LOANS), LOANS)) {
            try {
                String returned = f.get(4);
                library.restoreLoan(f.get(0), f.get(1), LocalDate.parse(f.get(2)), LocalDate.parse(f.get(3)),
                        returned.isEmpty() ? null : LocalDate.parse(returned));
            } catch (RuntimeException e) {
                throw corrupt(LOANS, f, e);
            }
        }
        return library;
    }

    private static List<List<String>> read(Path file, String name) throws IOException {
        List<List<String>> rows = new ArrayList<>();
        if (!Files.exists(file)) return rows;
        List<String> lines = Files.readAllLines(file);
        for (int i = 1; i < lines.size(); i++) {               // skip header
            if (!lines.get(i).isBlank()) rows.add(Csv.parse(lines.get(i)));
        }
        return rows;
    }

    private static void write(Path file, List<String> lines) throws IOException {
        Path tmp = file.resolveSibling(file.getFileName() + ".tmp");
        Files.write(tmp, lines);
        Files.move(tmp, file, StandardCopyOption.REPLACE_EXISTING);
    }

    private static IOException corrupt(String file, List<String> row, RuntimeException cause) {
        return new IOException("Corrupt data in " + file + " at row " + row + ": " + cause.getMessage(), cause);
    }
}
