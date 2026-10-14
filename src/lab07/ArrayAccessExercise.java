package lab07;

/**
 * Exercise 7.2 - Catch ArrayIndexOutOfBoundsException (an UNCHECKED exception).
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public final class ArrayAccessExercise {
    private ArrayAccessExercise() { }

    public static String elementAt(int[] data, int index) {
        try {
            return "data[" + index + "] = " + data[index];
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Error: index " + index + " is outside 0.." + (data.length - 1);
        }
    }
}
