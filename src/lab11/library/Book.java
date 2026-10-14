package lab11.library;

/**
 * A book: 21-day loan.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public class Book extends LibraryItem {
    private final String author;

    public Book(String id, String title, String author) {
        super(id, title);
        this.author = Validation.requireText(author, "author");
    }

    public String getAuthor() { return author; }

    @Override public int loanPeriodDays() { return 21; }
    @Override public String type()        { return "BOOK"; }
    @Override public String detail()      { return author; }

    @Override
    public String toString() { return super.toString() + " by " + author; }
}
