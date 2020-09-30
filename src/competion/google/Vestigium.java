package competion.google;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Vestigium {

    public static void main(String[] args) {
        Vestigium solution = new Vestigium();
        solution.scanAndSolve();
        //Scanner scanner = new Scanner(System.in);
    }

    public void scanAndSolve() {
        Scanner scanner = new Scanner(System.in);
        int testSize = scanner.nextInt();
        for (int tc = 1; tc <= testSize; tc++) {
            int squareSize = scanner.nextInt();
            int[][] square = new int[squareSize][squareSize];
            for (int i = 0; i < squareSize; i++) {
                for (int j = 0; j < squareSize; j++) {
                    square[i][j] = scanner.nextInt();
                }
            }
            int[] res = solve(square);
            System.out.println(String.format("Case #%s: %s %s %s", tc, res[0], res[1], res[2]));
        }
    }

    public int[] solve(int[][] square) {
        int trace = 0;
        int repeatedRowCount = 0;
        int repeatedColCount = 0;

        for (int i = 0; i < square.length; i++) {
            Set<Integer> rowCheck = new HashSet<>();
            Set<Integer> colCheck = new HashSet<>();
            boolean isRepeatedRow = false;
            boolean isRepeatedCol = false;
            for (int j = 0; j < square[0].length; j++) {
                if (rowCheck.contains(square[i][j])) {
                    isRepeatedRow = true;
                }
                if (colCheck.contains(square[j][i])) {
                    isRepeatedCol = true;
                }
                if (i == j) {
                    trace += square[i][j];
                }
                rowCheck.add(square[i][j]);
                colCheck.add(square[j][i]);
            }
            if (isRepeatedRow) {
                repeatedRowCount ++;
            }
            if (isRepeatedCol) {
                repeatedColCount ++;
            }
        }
        return new int[] {trace, repeatedRowCount, repeatedColCount};
    }
}
