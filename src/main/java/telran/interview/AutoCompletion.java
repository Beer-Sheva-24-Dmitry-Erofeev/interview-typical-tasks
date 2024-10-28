package telran.interview;

import java.util.SortedSet;
import java.util.TreeSet;

public class AutoCompletion {

    private final TreeSet<String> wordsSet;

    // Constructor
    public AutoCompletion() {
        wordsSet = new TreeSet<>(String.CASE_INSENSITIVE_ORDER); // Case-insensitive sorting
    }

    // Adds new word into auto-completion variants
    // Returns true if added
    // Returns false if the word already exists
    public boolean addWord(String word) {
        return wordsSet.add(word);
    }

    // Returns all words starting with a given prefix
    // Complexity of finding the variants is O[logN]
    public String[] getVariants(String prefix) {
        // Define the start and end range for the prefix
        String end = prefix + Character.MAX_VALUE;  // Last possible char for the prefix
        SortedSet<String> subSet = wordsSet.subSet(prefix, end);
        // Convert the subset to a String array
        return subSet.toArray(String[]::new);
    }
}
