package lab11.library;

/**
 * Capability contract: something that can be lent to a {@link Member} and given back.
 * (An interface, because borrowing is a CAPABILITY that is not tied to one class hierarchy.)
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public interface Borrowable {
    /** @throws ItemNotAvailableException if the item is already on loan */
    void borrow(Member member) throws ItemNotAvailableException;

    void giveBack();
}
