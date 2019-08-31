import java.util.ArrayList;
import java.util.List;

public class Stroke {

    public int strokesRequired(List<String> picture) {
        // Write your code here
        if (picture.size() < 1) {
            return 0;
        }
        int minStroke = 0;
        int row = picture.size();
        int col = picture.get(0).length();
        boolean [][] visit = new boolean[row][col];
        for (int i = 0; i < row; i ++) {
            for (int j = 0; j < col; j++) {
                visit[i][j] = false;
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (!visit[i][j]) {
                    findSameColorArea(visit, i, j, row, col, picture);
                    minStroke += 1;
                }
            }
        }

        return minStroke;
    }

    public void findSameColorArea(boolean[][] visit, int startRow, int startCol, int row, int col, List<String> picture) {
        visit[startRow][startCol] = true;
        int[][] nextPos = new int [][]{
                {startRow, startCol - 1},
                {startRow, startCol + 1},
                {startRow - 1, startCol},
                {startRow + 1, startCol}};

        for (int i = 0; i < nextPos.length; i++) {
            int[] pos = nextPos[i];
            if (isBound(pos[0], pos[1], row, col)
                    && picture.get(startRow).charAt(startCol) == picture.get(pos[0]).charAt(pos[1])
                    && !visit[pos[0]][pos[1]]) {
                findSameColorArea(visit, pos[0], pos[1], row, col, picture);
            }
        }
    }

    public boolean isBound(int i, int j, int row, int col) {
        if (i >= row || i < 0 || j >= col || j < 0) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        var list = new ArrayList<String>();
        list.add("aaaba");
        list.add("ababa");
        list.add("aaaca");

        var strocker = new Stroke();
        var result = strocker.strokesRequired(list);
        System.out.println(result);
    }
}
