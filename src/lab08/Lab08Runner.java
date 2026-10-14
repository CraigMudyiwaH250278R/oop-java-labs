package lab08;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Lab 8 runner.
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public class Lab08Runner {
    public static void main(String[] args) {
        System.out.println("=== Ex 8.1 ArrayList of 10 integers ===");
        List<Integer> nums = List.of(4, 8, 15, 16, 23, 42, 7, 3, 9, 1);
        System.out.println("List    : " + nums);
        System.out.println("Sum     : " + ListExercises.sum(nums));
        System.out.println("Max     : " + ListExercises.max(nums));
        System.out.println("Reversed: " + ListExercises.reversed(nums));

        System.out.println("\n=== Ex 8.2 Remove duplicate names with a Set ===");
        List<String> names = List.of("Tariro", "Farai", "Tariro", "Chipo", "Farai", "Rudo");
        System.out.println(names + " -> " + ListExercises.removeDuplicates(names));

        System.out.println("\n=== Ex 8.3 Generic Stack<T> ===");
        Stack<String> st = new Stack<>();
        st.push("a");
        st.push("b");
        st.push("c");
        System.out.println("peek=" + st.peek() + ", pop=" + st.pop() + ", size=" + st.size() + ", isEmpty=" + st.isEmpty());
        Stack<Integer> si = new Stack<>();          // same class, different type: type-safe
        si.push(10);
        System.out.println("Stack<Integer> pop=" + si.pop());

        System.out.println("\n=== Ex 8.4 Word frequency (count desc, then A-Z) ===");
        String text = "the quick brown fox jumps over the lazy dog the fox";
        Map<String, Integer> freq = WordFrequency.count(text);
        for (Map.Entry<String, Integer> e : WordFrequency.sortedByFrequency(freq)) {
            System.out.printf("%-6s %d%n", e.getKey(), e.getValue());
        }

        System.out.println("\n=== Ex 8.5 Generic bubbleSort ===");
        Integer[] ints = { 5, 2, 9, 1, 7 };
        String[] strs = { "pear", "apple", "mango", "banana" };
        Sorter.bubbleSort(ints);
        Sorter.bubbleSort(strs);
        System.out.println(Arrays.toString(ints));
        System.out.println(Arrays.toString(strs));
    }
}
