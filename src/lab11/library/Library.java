package lab11.library;

import java.time.Clock;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * The core service. Items and members live in {@code Map<String, ...>} collections keyed by id
 * (O(1) lookup; duplicate ids are impossible). Time comes from an injected {@link Clock}, so
 * overdue logic is testable without waiting for real days to pass.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public class Library {
    private final Map<String, LibraryItem> items = new LinkedHashMap<>();
    private final Map<String, Member> members = new LinkedHashMap<>();
    private final List<Loan> loans = new ArrayList<>();      // active AND past loans (history)
    private final Clock clock;

    public Library() { this(Clock.systemDefaultZone()); }

    public Library(Clock clock) { this.clock = Objects.requireNonNull(clock, "clock"); }

    public LocalDate today() { return LocalDate.now(clock); }

    // ------------------------------------------------------------------ registration

    public void addItem(LibraryItem item) {
        Objects.requireNonNull(item, "item");
        if (items.putIfAbsent(item.getId(), item) != null) {
            throw new IllegalArgumentException("An item with id " + item.getId() + " already exists");
        }
    }

    public void registerMember(Member member) {
        Objects.requireNonNull(member, "member");
        if (members.putIfAbsent(member.getId(), member) != null) {
            throw new IllegalArgumentException("A member with id " + member.getId() + " already exists");
        }
    }

    // ------------------------------------------------------------------ lookups

    public LibraryItem getItem(String id) {
        LibraryItem item = items.get(id);
        if (item == null) throw new IllegalArgumentException("No item with id " + id);
        return item;
    }

    public Member getMember(String id) {
        Member member = members.get(id);
        if (member == null) throw new IllegalArgumentException("No member with id " + id);
        return member;
    }

    public Collection<LibraryItem> getItems()  { return Collections.unmodifiableCollection(items.values()); }
    public Collection<Member> getMembers()     { return Collections.unmodifiableCollection(members.values()); }
    public List<Loan> getLoanHistory()         { return List.copyOf(loans); }

    // ------------------------------------------------------------------ borrowing

    /**
     * Lends an item to a member.
     * The member's limit is checked first and the item's availability second, and the state only
     * changes after BOTH checks pass, so a failed borrow leaves everything untouched.
     *
     * @throws IllegalArgumentException        if the member or item id is unknown
     * @throws MemberLimitExceededException    if the member already holds {@link Member#MAX_LOANS} loans
     * @throws ItemNotAvailableException       if the item is already on loan
     */
    public Loan borrow(String memberId, String itemId) throws ItemNotAvailableException, MemberLimitExceededException {
        Member member = getMember(memberId);          // 1. look up (IllegalArgumentException if missing)
        LibraryItem item = getItem(itemId);
        if (!member.canBorrow()) {                    // 2. check the member's limit...
            throw new MemberLimitExceededException(member.getId(), member.getName(), Member.MAX_LOANS);
        }
        item.borrow(member);                          //    ...and the item's availability
        Loan loan = Loan.start(item, member, today());  // 3. record the loan
        loans.add(loan);
        member.addLoan(loan);
        return loan;
    }

    /**
     * Returns an item.
     *
     * @return the completed loan (check {@link Loan#daysLate} for lateness)
     * @throws IllegalArgumentException if the item id is unknown
     * @throws IllegalStateException    if the item is not currently on loan
     */
    public Loan giveBack(String itemId) {
        LibraryItem item = getItem(itemId);
        Loan loan = loans.stream()
                .filter(l -> l.isActive() && l.getItem() == item)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Item " + itemId + " is not currently on loan"));
        loan.markReturned(today());
        item.giveBack();
        loan.getMember().removeLoan(loan);
        return loan;
    }

    // ------------------------------------------------------------------ queries (streams)

    /** Active loans past their due date, most overdue first. */
    public List<Loan> overdueLoans() {
        LocalDate today = today();
        return loans.stream()
                .filter(l -> l.isOverdue(today))
                .sorted(Comparator.comparing(Loan::getDueOn))
                .toList();
    }

    /** Case-insensitive title search, sorted by title. */
    public List<LibraryItem> searchByTitle(String text) {
        String needle = text.toLowerCase();
        return items.values().stream()
                .filter(i -> i.getTitle().toLowerCase().contains(needle))
                .sorted(Comparator.comparing(LibraryItem::getTitle, String.CASE_INSENSITIVE_ORDER))
                .toList();
    }

    // ------------------------------------------------------------------ persistence support

    /** Rebuilds a stored loan (used only by {@link LibraryStore}); an active loan re-marks its item as on loan. */
    void restoreLoan(String itemId, String memberId, LocalDate borrowedOn, LocalDate dueOn, LocalDate returnedOn) {
        LibraryItem item = getItem(itemId);
        Member member = getMember(memberId);
        Loan loan = new Loan(item, member, borrowedOn, dueOn, returnedOn);
        if (loan.isActive()) {
            try {
                item.borrow(member);
            } catch (ItemNotAvailableException e) {
                throw new IllegalStateException("Item " + itemId + " has two active loans in the data file", e);
            }
            member.addLoan(loan);
        }
        loans.add(loan);
    }
}
