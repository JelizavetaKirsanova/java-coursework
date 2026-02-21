package types;

public class Pmd {

    public static void main(String[] args) {
        boolean[][] table = getSampleTable();

        printTable(table);

        System.out.println(containsTrueCell(table));
        System.out.println(countTrueRow(table));
    }

    private static void printTable(boolean[][] table) {
        for (boolean[] row : table) {
            for (boolean element : row) {
                System.out.print(element ? "X" : "O");
            }
            System.out.println();
        }
    }

    // intentionally bad code
    public static boolean containsTrueCell(boolean[][] matrix) {
        for (boolean[] row : matrix) {
            for (boolean cell : row) {
                if (cell) {
                    return true;
                }
            }
        }
        return false;
    }

    // intentionally bad code
    public static int countTrueRow(boolean[][] matrix) {
        for (boolean[] row : matrix) {
            for (boolean cellCheck : row) {
                if (cellCheck) {
                    int count = 0;
                    for (boolean cell : row) {
                        if (cell) {
                            count++;
                        }
                    }
                    return count;
                }
            }
        }
        return -1;
    }

    private static boolean[][] getSampleTable() {
        boolean[][] matrix = new boolean[3][3];

        matrix[2][1] = true;
        matrix[2][2] = true;

        return matrix;
    }

}
