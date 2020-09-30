package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class IntervalIntersection {

    public static void main(String[] args) {
        //int[][] A = new int[][] {{0,2},{5,10},{13,23},{24,25}};
        //int[][] B = new int[][] {{1,5},{8,12},{15,24},{25,26}};
        //[[8,10],[12,15]]
        //int[][] A = new int[][] {{8,15}};
        //int[][] B = new int[][] {{2,6},{8,10},{12,20}};

        //[[4,5],[9,10],[11,12],[14,15],[16,20]]
        int[][] A =  new int[][] {{3,5},{9,20}};
        int[][] B = new int[][] {{4,5},{7,10},{11,12},{14,15},{16,20}};

        IntervalIntersection intervalIntersection = new IntervalIntersection();
        int[][] res = intervalIntersection.intervalIntersection2(A, B);
        System.out.println(Arrays.deepToString(res));

    }
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> res = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                checkOrAddIntersection(A[i], B[j], res);
            }
        }
        return res.toArray(new int[][]{});
    }

    void checkOrAddIntersection(int[] interval1, int[] interval2, List<int[]> res) {
        int maxStart = Math.max(interval1[0], interval2[0]);
        int minEnd = Math.min(interval1[1], interval2[1]);
        if (minEnd >= maxStart) {
            res.add(new int[] {maxStart, minEnd});
        }
    }

    /////////////////////////////////////////////////////////////////////////////////
    // v2

    public int[][] intervalIntersection2(int[][] A, int[][] B) {
        List<int[]> res = new ArrayList<>();
        int[][] AB = combineIntervals(A, B);
        for (int i = 0; i < AB.length - 1; i++) {
            checkOrAddIntersection(AB[i], AB[i + 1], res);
        }
        return res.toArray(new int[][] {});
    }

    public int[][] combineIntervals(int[][] A, int[][] B) {
        int[][] AB = new int[A.length + B.length][2];
        int index = 0;
        int indexA = 0;
        int indexB = 0;
        while (indexA < A.length && indexB < B.length) {
            if (A[indexA][1] <= B[indexB][1]) {
                AB[index ++] = A[indexA ++];
            } else {
                AB[index ++] = B[indexB ++];
            }
        }
        while (indexA < A.length) {
            AB[index ++] = A[indexA ++];
        }
        while (indexB < B.length) {
            AB[index ++] = B[indexB ++];
        }
        return AB;
    }


}