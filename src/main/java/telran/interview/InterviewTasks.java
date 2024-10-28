package telran.interview;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class InterviewTasks {

    // Returns maximal positive value for which exists negative one with the same absolute value
    // If there is no pair of positive and negative values with the same absolute value, the method returns -1
    // Complexity O[N], only one pass over the elements
    static public int getMaxWithNegativePresentation(int[] array) {

        // Since we need linear time (complexity O[N]) we introduce HashSet for searching
        Set<Integer> set = new HashSet<>();
        // Setting the default resulting value to "-1" in case of no pair meeting our condition  
        int maxValue = -1;
        // Iterating the whole array once
        for (int num : array) {
            // Add the number to the set
            set.add(num);

            // Check if the negative counterpart exists in the set
            if (set.contains(-num)) {
                // If so - update maxValue if this is the largest we've seen
                maxValue = Math.max(maxValue, Math.abs(num));
            }
        }

        // If no valid pair was found, maxValue will remain -1
        return maxValue;
    }

    // ----------------------------------------------------------------------------------------------------------
    // RolesHistory is the list containg dates and roles assigned to the employees at the appropriate dates
    // For example, date => 2019-01-01, role => Developer means that some employee (no matter who) was taken
    // Developer role at 2019-01-01
    // Create List<DateRole> with roles matching with the given dates
    // Most effective data structure
    public static List<DateRole> assignRoleDates(List<DateRole> rolesHistory,
            List<LocalDate> dates) {

        List<DateRole> result = new ArrayList<>();

        for (LocalDate date : dates) {
            // Binary search to find the appropriate role
            int index = findRoleForDate(rolesHistory, date);
            String role = (index >= 0) ? rolesHistory.get(index).role() : null;
            result.add(new DateRole(date, role));
        }

        return result;
    }

    // Helper method to find the index of the latest role that is <= the given date
    private static int findRoleForDate(List<DateRole> rolesHistory, LocalDate date) {
        int left = 0;
        int right = rolesHistory.size() - 1;
        int resultIndex = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            LocalDate midDate = rolesHistory.get(mid).date();

            if (midDate.isEqual(date) || midDate.isBefore(date)) {
                resultIndex = mid; // Candidate for the result
                left = mid + 1; // Search in the right half for more recent roles
            } else {
                right = mid - 1; // Search in the left half
            }
        }

        return resultIndex;
    }

    //----------------------------------------------------------------------------------------------------------
    public static boolean isAnagram(String word, String anagram) {
        // Returns true if "anagram" string contains all
        // letters from "word" in another order (case sensitive)
        // Complexity - O[N], (sorting is disallowed)

        boolean res = false;

        // They could be anagrams only if they have the same length and they are not the same
        if (word.length() == anagram.length() && !word.equals(anagram)) {

            Map<Character, Integer> charCount = new HashMap<>();

            // Count characters in the first word
            for (char c : word.toCharArray()) {
                charCount.put(c, charCount.getOrDefault(c, 0) + 1);
            }

            // Subtract counts using the second word
            for (char c : anagram.toCharArray()) {

                // If the character doesn't exist in the map, they're not anagrams
                if (!charCount.containsKey(c)) {
                    res = false;
                    break; // To not waste time in this loop
                    // I know that our professor doesn't like breaks, but
                    // I'm not sure how to make it more elegant
                }
                charCount.put(c, charCount.get(c) - 1);
                if (charCount.get(c) == 0) {
                    charCount.remove(c);  // Remove to keep the map clean
                }
            }
            // If the map is empty, the words are anagrams
            if (charCount.isEmpty()) {
                res = true;
            }

        }
        return res;
    }

    //----------------------------------------------------------------------------------------------------------
    /**
     *
     * @param array
     * @param sum
     * @return true if a given array comprises of two number, summing of which
     * gives the value equaled to a given "sum" value
     */
    static public boolean hasSumTwo(int[] array, int sum) {
        HashSet<Integer> helpers = new HashSet<>();
        int index = 0;
        while (index < array.length && !helpers.contains(sum - array[index])) {
            helpers.add(array[index++]);
        }
        return index < array.length;
    }

}
