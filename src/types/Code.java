package types;

import java.util.Arrays;
import java.util.Random;

public class Code {

    private static final boolean[][] TABLE = getSampleTable();

    public static void main(String[] args) {
        int[] numbers = {1, 3, -2, 9};
        System.out.println(sum(numbers)); // 11
    }

    public static int sum(int[] numbers) {
        int sum = 0;
        for (int value : numbers) {
            sum += value;
        }
        return sum;
    }

    public static double average(int[] numbers) {
        if (numbers.length == 0) {
            return 0;
        }
        return (double) sum(numbers) / numbers.length;
    }

    public static Integer minimumElement(int[] numbers) {
        if (numbers.length == 0) {
            return null;
        }
        int min = numbers[0];
        for (int value : numbers) {
            if (value < min) {
                min = value;
            }
        }
        return min;
    }

    public static String asString(int[] numbers) {
        if (numbers.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numbers.length; i++) {
            sb.append(numbers[i]);
            if (i < numbers.length - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    public static Character mode(String input) {
        if (input.isEmpty()) {
            return null;
        }

        int[] freq = new int[256];
        for (char c : input.toCharArray()) {
            freq[c]++;
        }

        char mostFrequent = input.charAt(0);
        int maxCount = freq[mostFrequent];
        for (char c : input.toCharArray()) {
            if (freq[c] > maxCount) {
                mostFrequent = c;
                maxCount = freq[c];
            }
        }
        return mostFrequent;
    }

    public static String squareDigits(String s) {
        StringBuilder result = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                int n = Character.getNumericValue(c);
                result.append(n * n);
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    public static boolean isIsolated(int row, int col) {
        if (!TABLE[row][col]) {
            return false;
        }

        for (int i = -1; i <= 1; i++) {
            int newRow = row + i;
            if (newRow < 0 || newRow >= TABLE.length) {
                continue;
            }

            for (int j = -1; j <= 1; j++) {
                int newCol = col + j;
                if (newCol < 0 || newCol >= TABLE[0].length) {
                    continue;
                }
                if (i == 0 && j == 0) {
                    continue;
                }
                if (TABLE[newRow][newCol]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int isolatedSquareCount() {
        int count = 0;
        for (int row = 0; row < TABLE.length; row++) {
            for (int col = 0; col < TABLE[row].length; col++) {
                if (isIsolated(row, col)) {
                    count++;
                }
            }
        }
        return count;
    }

    private static void printTable(boolean[][] table) {
        for (boolean[] row : table) {
            System.out.println(Arrays.toString(row));
        }
    }

    private static boolean[][] getSampleTable() {
        boolean[][] table = new boolean[10][10];

        Random r = new Random(5);
        for (boolean[] row : table) {
            for (int i = 0; i < row.length; i++) {
                row[i] = r.nextInt(5) < 2;
            }
        }

        return table;
    }
}
