package lab11.library;

import java.util.ArrayList;
import java.util.List;

/**
 * A library member. Owns the list of loans currently held and enforces the loan limit rule
 * ({@link #canBorrow()}). The list is private and only exposed as an unmodifiable copy.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public class Member {
    /** Business rule: maximum simultaneous loans per member. */
    public static final int MAX_LOANS = 3;

    private final String id;
    private final String name;
    private final List<Loan> activeLoans = new ArrayList<>();

    public Member(String id, String name) {
        this.id = Validation.requireText(id, "member id");
        this.name = Validation.requireText(name, "member name");
    }

    public String getId()   { return id; }
    public String getName() { return name; }

    public boolean canBorrow() { return activeLoans.size() < MAX_LOANS; }

    /** @return a read-only snapshot of the loans currently held */
    public List<Loan> getActiveLoans() { return List.copyOf(activeLoans); }

    void addLoan(Loan loan)    { activeLoans.add(loan); }
    void removeLoan(Loan loan) { activeLoans.remove(loan); }

    @Override
    public String toString() {
        return String.format("%-5s %s (%d/%d loans)", id, name, activeLoans.size(), MAX_LOANS);
    }
}
