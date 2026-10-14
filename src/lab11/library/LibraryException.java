package lab11.library;

/**
 * Base class of the library's CHECKED business exceptions, so callers can catch "any expected
 * library failure" in one clause while still handling the specific subclasses separately.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public class LibraryException extends Exception {
    private static final long serialVersionUID = 1L;

    public LibraryException(String message) { super(message); }
}
