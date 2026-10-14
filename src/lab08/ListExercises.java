package lab08;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Exercises 8.1 and 8.2 - List statistics and de-duplication with a Set.
 * Declared as the INTERFACE type (List, Set), per Appendix A: program to an interface.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public final class ListExercises {
    private ListExercises() { }

    public static int sum(List<Integer> nums) {
        int total = 0;
        for (int n : nums) total += n;
        return total;
    }

    public static int max(List<Integer> nums) {
        return Collections.max(nums);
    }

    public static List<Integer> reversed(List<Integer> nums) {
        List<Integer> copy = new ArrayList<>(nums);     // do not mutate the caller's list
        Collections.reverse(copy);
        return copy;
    }

    /** LinkedHashSet removes duplicates AND keeps first-seen order. */
    public static List<String> removeDuplicates(List<String> names) {
        Set<String> unique = new LinkedHashSet<>(names);
        return new ArrayList<>(unique);
    }
}
