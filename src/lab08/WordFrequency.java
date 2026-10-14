package lab08;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Exercise 8.4 - Word frequencies in a Map, printed sorted by count descending (ties alphabetical).
 *
 * @author Craig Mudyiwa (H250278R), Software Engineering
 */
public final class WordFrequency {
    private WordFrequency() { }

    public static Map<String, Integer> count(String text) {
        Map<String, Integer> freq = new HashMap<>();
        for (String word : text.toLowerCase().split("[^a-z']+")) {
            if (!word.isEmpty()) {
                freq.merge(word, 1, Integer::sum);
            }
        }
        return freq;
    }

    public static List<Map.Entry<String, Integer>> sortedByFrequency(Map<String, Integer> freq) {
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(freq.entrySet());
        entries.sort(Map.Entry.<String, Integer>comparingByValue().reversed()
                .thenComparing(Map.Entry.comparingByKey()));
        return entries;
    }
}
