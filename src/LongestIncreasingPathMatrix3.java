/**
 * https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
 */
public class LongestIncreasingPathMatrix3 {
    int [][] matrix;
    int row;
    int col;
    int [][] count;
    int[] trace;

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        this.matrix = matrix;
        this.row = matrix.length;
        this.col = matrix[0].length;
        this.count = new int[row][col];
        int max = 1;
        int N = row * col;
        this.trace = new int[N];
        int maxAt = 0;
        for (int i = 0; i < N; i++) {
            trace[i] = -1;
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (count[i][j] == 0) {
                    DFSAndCount(i, j);
                }
                if (max < count[i][j]) {
                    max = count[i][j];
                    maxAt = i * col + j;
                }
            }
        }
        // for print the longest increasing path
        do {
            System.out.print(matrix[maxAt / col][maxAt % col] + " -> ");
            maxAt = trace[maxAt];
        } while (maxAt != -1);
        return max;
    }

    private void DFSAndCount(int rowPos, int colPos) {
        int[][] nextCandidates = new int[][] {{rowPos + 1, colPos}, {rowPos - 1, colPos}, {rowPos, colPos + 1}, {rowPos, colPos - 1}};
        int tmpMax = 0;
        int v = rowPos * col + colPos;
        for (int[] nextPos : nextCandidates) {
            if (canMove(rowPos, colPos, nextPos[0], nextPos[1])) {
                if (count[nextPos[0]][nextPos[1]] == 0) {
                    DFSAndCount(nextPos[0], nextPos[1]);
                }
                if (tmpMax < count[nextPos[0]][nextPos[1]]) {
                    tmpMax = count[nextPos[0]][nextPos[1]];
                    trace[v] = nextPos[0] * col + nextPos[1];
                }
            }
        }
        count[rowPos][colPos] = tmpMax + 1;
    }

    private boolean canMove(int currRow, int currCol, int nextRow, int nextCol) {
        return nextRow >= 0 && nextRow < row
                && nextCol >= 0 && nextCol < col
                && matrix[nextRow][nextCol] > matrix[currRow][currCol];
    }
}



class TestLongestIncreasingPathMatrix3 {
    public static void main(String[] args) {
        var tester = new TestLongestIncreasingPathMatrix3();
        var matrix1 = new int[][] {{9, 9, 4}, {6, 6, 8}, {2, 1, 1}}; // 4
        tester.test(matrix1, 4);

        var matrix2 = new int[][] {{3, 4, 5}, {3, 2, 6}, {2, 2, 1}}; // 4
        tester.test(matrix2, 4);

        var matrix3 = new int[][] {{0,1,2,3,4,5,6,7,8,9},{19,18,17,16,15,14,13,12,11,10},{20,21,22,23,24,25,26,27,28,29},{39,38,37,36,35,34,33,32,31,30},{40,41,42,43,44,45,46,47,48,49},{59,58,57,56,55,54,53,52,51,50},{60,61,62,63,64,65,66,67,68,69},{79,78,77,76,75,74,73,72,71,70},{80,81,82,83,84,85,86,87,88,89},{99,98,97,96,95,94,93,92,91,90},{100,101,102,103,104,105,106,107,108,109},{119,118,117,116,115,114,113,112,111,110},{120,121,122,123,124,125,126,127,128,129},{139,138,137,136,135,134,133,132,131,130},{0,0,0,0,0,0,0,0,0,0}};
        tester.test(matrix3, 140);

        var matrix4 = new int[][] {{7,7,5},{2,4,6},{8,2,0}};
        tester.test(matrix4, 4);
    }

    public void test(int[][] matrix, int expected) {
        var path = new LongestIncreasingPathMatrix3();
        var res = path.longestIncreasingPath(matrix);
        if (res != expected) {
            System.out.println("Test failed, output is: " + res + ", and expected is : " + expected);
        } else {
            System.out.println("Test passed");
        }
        System.out.println("----------------------------------");
    }
}