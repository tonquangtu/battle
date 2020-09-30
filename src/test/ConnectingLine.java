package test;

import java.util.ArrayList;
import java.util.List;

public class ConnectingLine {

    public static void main(String[] args) {
        ConnectingLine connectingLine = new ConnectingLine();
        int[] A = new int[] {2,5,1,2,5};
        int[] B = new int[] {10,5,2,1,5,2};
        System.out.println(connectingLine.maxUncrossedLines(A, B));

    }
    public int maxUncrossedLines(int[] A, int[] B) {
        List<Line> lines = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                if (A[i] == B[j]) {
                    lines.add(new Line(i, j));
                    break;
                }
            }
        }
        return countUnIntersectLines(lines);
    }

    int countUnIntersectLines(List<Line> lines) {
        int count = 0;
        for (int i = 0; i < lines.size(); i++) {
            int intersect = countIntersect(i, lines);
            if (intersect < 1) {
                count ++;
            } else {
                lines.get(i).active = false;
            }
        }
        return count;
    }

    int countIntersect(int checkIndex, List<Line> lines) {
        int intersect = 0;
        Line checkLine = lines.get(checkIndex);
        for (int i = 0; i < lines.size(); i++) {
            Line line = lines.get(i);
           if (i != checkIndex && line.active && !isNotIntersect(checkLine, line)) {
               intersect++;
           }
        }
        return intersect;
    }

    boolean isNotIntersect(Line line1, Line line2) {
        return (line1.start < line2.start && line1.end < line2.end)
            || (line1.start > line2.start && line1.end > line2.end);
    }

    class Line {
        int start;
        int end;
        boolean active = true;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
