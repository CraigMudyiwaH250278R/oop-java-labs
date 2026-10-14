package lab11.library;

/**
 * Thrown when someone tries to borrow an item that is already on loan.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public class ItemNotAvailableException extends LibraryException {
    private static final long serialVersionUID = 1L;

    private final String itemId;

    public ItemNotAvailableException(String itemId, String title) {
        super("Item " + itemId + " (" + title + ") is not available: it is already on loan");
        this.itemId = itemId;
    }

    public String getItemId() { return itemId; }
}
