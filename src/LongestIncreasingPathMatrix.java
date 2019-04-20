/**
 * https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
 */
// Solution 1: using simple backtracking
public class LongestIncreasingPathMatrix {
    private int max;
    private int[][] matrix;
    private int row;
    private int col;
    private int[][] dp; // backtracking with remember memory

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }

        this.max = 1;
        this.matrix = matrix;
        this.row = matrix.length;
        this.col = matrix[0].length;
        this.dp = new int[row][col];

        findPath(0, 0, true);
        return max;
    }

    // isStart var to intended the current position (rowPos, colPos) is starting point of current path
    private int findPath(int rowPos, int colPos, boolean isStart) {
        int currCount = 1;
        if (dp[rowPos][colPos] != 0) {
            currCount = dp[rowPos][colPos];
        } else {
            int [][] nextPositions = {{rowPos + 1, colPos}, {rowPos - 1, colPos}, {rowPos, colPos + 1}, {rowPos, colPos - 1}};
            for (int[] pos : nextPositions) {
                if (canMove(rowPos, colPos, pos[0], pos[1])) {
                    int tmpCount = findPath(pos[0], pos[1], false) + 1;
                    if (currCount < tmpCount) {
                        currCount = tmpCount;
                    }
                }
            }
            dp[rowPos][colPos] = currCount;
        }

        if (max < currCount) {
            max = currCount;
        }

        if (isStart) {
            if (colPos < col - 1) {
                findPath(rowPos, colPos + 1, true);
            } else if (rowPos < row - 1) {
                findPath(rowPos + 1,  0, true);
            }
        }

        return currCount;
    }

    private boolean canMove(int currRow, int currCol, int nextRow, int nextCol) {
        return nextRow >= 0 && nextRow < row
                && nextCol >= 0 && nextCol < col
                && matrix[nextRow][nextCol] > matrix[currRow][currCol];
    }
}

class TestLongestIncreasingPathMatrix {
    public static void main(String[] args) {
        var tester = new TestLongestIncreasingPathMatrix();
        var matrix1 = new int[][] {{9, 9, 4}, {6, 6, 8}, {2, 1, 1}}; // 4
        tester.test(matrix1, 4);

        var matrix2 = new int[][] {{3, 4, 5}, {3, 2, 6}, {2, 2, 1}}; // 4
        tester.test(matrix2, 4);

        var matrix3 = new int[][] {{0,1,2,3,4,5,6,7,8,9},{19,18,17,16,15,14,13,12,11,10},{20,21,22,23,24,25,26,27,28,29},{39,38,37,36,35,34,33,32,31,30},{40,41,42,43,44,45,46,47,48,49},{59,58,57,56,55,54,53,52,51,50},{60,61,62,63,64,65,66,67,68,69},{79,78,77,76,75,74,73,72,71,70},{80,81,82,83,84,85,86,87,88,89},{99,98,97,96,95,94,93,92,91,90},{100,101,102,103,104,105,106,107,108,109},{119,118,117,116,115,114,113,112,111,110},{120,121,122,123,124,125,126,127,128,129},{139,138,137,136,135,134,133,132,131,130},{0,0,0,0,0,0,0,0,0,0}};
        tester.test(matrix3, 140);
    }

    public void test(int[][] matrix, int expected) {
        var path = new LongestIncreasingPathMatrix();
        var res = path.longestIncreasingPath(matrix);
        if (res != expected) {
            System.out.println("Test failed, output is: " + res + ", and expected is : " + expected);
        } else {
            System.out.println("Test passed");
        }
    }
}
