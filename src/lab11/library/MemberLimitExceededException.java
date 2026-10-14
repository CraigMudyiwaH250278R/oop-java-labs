package lab11.library;

/**
 * Thrown when a member already holds the maximum number of loans ({@link Member#MAX_LOANS}).
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public class MemberLimitExceededException extends LibraryException {
    private static final long serialVersionUID = 1L;

    private final String memberId;

    public MemberLimitExceededException(String memberId, String name, int limit) {
        super("Member " + memberId + " (" + name + ") has reached the limit of " + limit + " loans");
        this.memberId = memberId;
    }

    public String getMemberId() { return memberId; }
}
