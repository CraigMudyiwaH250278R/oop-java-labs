package lab11.library;

import java.util.Objects;

/**
 * ABSTRACTION + INHERITANCE: what every loanable item has (id, title, availability) while each
 * subclass supplies its own loan period (POLYMORPHISM via {@link #loanPeriodDays()}).
 * State is private; {@link #borrow} and {@link #giveBack} are the only ways to change availability.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public abstract class LibraryItem implements Borrowable {
    private final String id;
    private final String title;
    private boolean available = true;

    protected LibraryItem(String id, String title) {
        this.id = Validation.requireText(id, "item id");
        this.title = Validation.requireText(title, "title");
    }

    /** How long this kind of item may be kept. Decided by each subclass. */
    public abstract int loanPeriodDays();

    /** Short type code used by the CSV store ("BOOK", "DVD"). */
    public abstract String type();

    /** The one extra attribute of the subclass, as text, for the CSV store. */
    public abstract String detail();

    @Override
    public void borrow(Member member) throws ItemNotAvailableException {
        Objects.requireNonNull(member, "member");
        if (!available) {
            throw new ItemNotAvailableException(id, title);
        }
        available = false;
    }

    @Override
    public void giveBack() { available = true; }

    public String getId()        { return id; }
    public String getTitle()     { return title; }
    public boolean isAvailable() { return available; }

    @Override
    public String toString() {
        return String.format("[%s] %-4s %s%s (%d-day loan)", type(), id, title, available ? "" : "  <on loan>", loanPeriodDays());
    }
}
