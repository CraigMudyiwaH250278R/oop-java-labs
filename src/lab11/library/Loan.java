package lab11.library;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * One loan of one item to one member. Dates are fixed at creation except {@code returnedOn}, which is
 * set once by {@link #markReturned}. A loan with no return date is ACTIVE.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public final class Loan {
    private final LibraryItem item;
    private final Member member;
    private final LocalDate borrowedOn;
    private final LocalDate dueOn;
    private LocalDate returnedOn;

    Loan(LibraryItem item, Member member, LocalDate borrowedOn, LocalDate dueOn, LocalDate returnedOn) {
        this.item = Objects.requireNonNull(item, "item");
        this.member = Objects.requireNonNull(member, "member");
        this.borrowedOn = Objects.requireNonNull(borrowedOn, "borrowedOn");
        this.dueOn = Objects.requireNonNull(dueOn, "dueOn");
        if (dueOn.isBefore(borrowedOn)) {
            throw new IllegalArgumentException("due date cannot be before the borrow date");
        }
        if (returnedOn != null && returnedOn.isBefore(borrowedOn)) {
            throw new IllegalArgumentException("return date cannot be before the borrow date");
        }
        this.returnedOn = returnedOn;
    }

    /** Starts a new loan; the due date comes from the item's own (polymorphic) loan period. */
    static Loan start(LibraryItem item, Member member, LocalDate today) {
        return new Loan(item, member, today, today.plusDays(item.loanPeriodDays()), null);
    }

    void markReturned(LocalDate date) {
        if (returnedOn != null) {
            throw new IllegalStateException("loan already returned on " + returnedOn);
        }
        if (date.isBefore(borrowedOn)) {
            throw new IllegalArgumentException("return date cannot be before the borrow date");
        }
        returnedOn = date;
    }

    public boolean isActive() { return returnedOn == null; }

    /** Overdue = still out and past its due date. */
    public boolean isOverdue(LocalDate today) { return isActive() && today.isAfter(dueOn); }

    /** Days late as of {@code asOf} (or as of the return date for a returned loan); never negative. */
    public long daysLate(LocalDate asOf) {
        LocalDate reference = returnedOn != null ? returnedOn : asOf;
        return Math.max(0, ChronoUnit.DAYS.between(dueOn, reference));
    }

    public LibraryItem getItem()    { return item; }
    public Member getMember()       { return member; }
    public LocalDate getBorrowedOn() { return borrowedOn; }
    public LocalDate getDueOn()     { return dueOn; }
    public LocalDate getReturnedOn() { return returnedOn; }

    @Override
    public String toString() {
        return item.getTitle() + " -> " + member.getName() + ", due " + dueOn
                + (returnedOn != null ? ", returned " + returnedOn : "");
    }
}
