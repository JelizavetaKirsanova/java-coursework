package junit;

import java.util.LinkedHashSet;
import java.util.HashSet;

public class Code {

    public static boolean isSpecial(int candidate) {
        int remainder = candidate % 11;
        return remainder == 0 || remainder == 1 || remainder == 2 || remainder == 3;
    }

    public static int longestStreak(String inputString) {
        if (inputString == null || inputString.isEmpty()) {
            return 0;
        }

        int longest = 1;
        int current = 1;

        for (int i = 1; i < inputString.length(); i++) {
            if (inputString.charAt(i) == inputString.charAt(i - 1)) {
                current++;
                if (current > longest) {
                    longest = current;
                }
            } else {
                current = 1;
            }
        }

        return longest;
    }

    public static Character mode(String input) {
        if (input == null || input.isEmpty()) return null;

        char mostFrequent = input.charAt(0);
        int maxCount = 0;

        for (int i = 0; i < input.length(); i++) {
            char current = input.charAt(i);
            int count = getCharacterCount(input, current);

            if (count > maxCount) { // при равных остаётся первый
                maxCount = count;
                mostFrequent = current;
            }
        }

        return mostFrequent;
    }

    public static int getCharacterCount(String allCharacters, char targetCharacter) {
        if (allCharacters == null || allCharacters.isEmpty()) return 0;

        int count = 0;
        for (int i = 0; i < allCharacters.length(); i++) {
            if (allCharacters.charAt(i) == targetCharacter) count++;
        }
        return count;
    }

    public static int[] removeDuplicates(int[] integers) {
        if (integers == null) return null;

        LinkedHashSet<Integer> set = new LinkedHashSet<>();
        for (int n : integers) set.add(n);

        int[] result = new int[set.size()];
        int index = 0;
        for (int n : set) result[index++] = n;
        return result;
    }

    public static int sumIgnoringDuplicates(int[] integers) {
        if (integers == null) return 0;

        HashSet<Integer> set = new HashSet<>();
        for (int n : integers) set.add(n);

        int sum = 0;
        for (int n : set) sum += n;
        return sum;
    }
}