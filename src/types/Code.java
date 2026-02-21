package types;

import java.util.Arrays;
import java.util.Random;

public class Code {

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

    public static Integer minimumElement(int[] integers) {
        if (integers.length == 0) {
            return null;
        }
        int min = integers[0];
        for (int value : integers) {
            if (value < min) {
                min = value;
            }
        }
        return min;
    }

    public static String asString(int[] elements) {
        if (elements.length == 0) {
            return "";
        }

        String result = "";
        for (int i = 0; i < elements.length; i++) {
            result += elements[i];
            if (i < elements.length - 1) {
                result += ", ";
            }
        }
        return result;
    }

    public static Character mode(String input) {
        if (input.length() == 0) {
            return null;
        }


        int maxCount = 0;
        Character mostFrequent = null;
        for (int i = 0; i < input.length(); i++) {
            char current = input.charAt(i);
            int count = 0;

            for (int j = 0; j < input.length(); j++) {
                if (input.charAt(j) == current) {
                    count++;
                }
            }
            if (count > maxCount) {
                maxCount = count;
                mostFrequent = current;
            }
        }
        return mostFrequent;
    }

    public static String squareDigits(String s) {

        String result = "";


        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                int number = Integer.parseInt(Character.toString(c));
                result += number * number;
            } else {
                result += c;
            }
        }

        return result;
    }

    public static int isolatedSquareCount() {
        boolean[][] table = getSampleTable();
        int isolatedCount = 0;
        for (int row = 0; row < table.length; row++) {
            for (int col = 0; col < table[row].length; col++) {
                if (table[row][col] && isIsolated( row, col)) {
                    isolatedCount++;
                }
            }
        }
        return isolatedCount;
    }


    public static boolean isIsolated( int row, int col) {
        boolean[][] table = getSampleTable();

        printTable(table);

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue; // skip self
                int newRow = row + i;
                int newCol = col + j;
                if (newRow >= 0 && newRow < table.length &&
                        newCol >= 0 && newCol < table[0].length) {
                    if (table[newRow][newCol]) {
                        return false; // has a neighbor that is true
                    }
                }
            }
        }
        return true;
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
