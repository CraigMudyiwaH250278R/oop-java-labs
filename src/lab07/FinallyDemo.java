package lab07;

/**
 * Exercise 7.5 - return inside try, print inside finally.
 *
 * PREDICTION: "in finally" prints BEFORE the caller receives the value 1, and then 1 is returned.
 * WHY: the JVM evaluates the return expression, stores it, runs the finally block, and only
 * then completes the return. finally runs on every exit path (normal, return, break, exception);
 * the only ways it is skipped are System.exit() or JVM/hardware failure.
 *
 * Second case: if finally itself returns, it OVERRIDES the try's return value (bad practice!).
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public final class FinallyDemo {
    private FinallyDemo() { }

    @SuppressWarnings("finally")
    static int tryReturn() {
        try {
            System.out.println("in try, returning 1");
            return 1;
        } finally {
            System.out.println("in finally (runs before the value is handed back)");
        }
    }

    @SuppressWarnings("finally")
    static int finallyOverrides() {
        try {
            return 1;
        } finally {
            return 2;                    // overrides: never do this in real code
        }
    }

    public static void main(String[] args) {
        System.out.println("tryReturn() gave " + tryReturn());
        System.out.println("finallyOverrides() gave " + finallyOverrides());
    }
}
