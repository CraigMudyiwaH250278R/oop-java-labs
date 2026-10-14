package lab11.library;

/**
 * Shared input-validation helpers (single place for the rules, so no duplication).
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
final class Validation {
    private Validation() { }

    /** @return the trimmed text; rejects null, blank and multi-line values (multi-line would break CSV storage). */
    static String requireText(String value, String field) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(field + " must not be blank");
        }
        if (value.indexOf('\n') >= 0 || value.indexOf('\r') >= 0) {
            throw new IllegalArgumentException(field + " must be a single line");
        }
        return value.trim();
    }
}
