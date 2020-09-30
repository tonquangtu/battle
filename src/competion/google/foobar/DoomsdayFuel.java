package competion.google.foobar;

import java.util.List;

import javax.xml.crypto.dsig.Transform;

/**
 * Doomsday Fuel
 * =============
 *
 * Making fuel for the LAMBCHOP's reactor core is a tricky process because of the exotic matter involved. It starts
 * as raw ore, then during processing, begins randomly changing between forms, eventually reaching a stable form.
 * There may be multiple stable forms that a sample could ultimately reach, not all of which are useful as fuel.
 *
 * Commander Lambda has tasked you to help the scientists increase fuel creation efficiency by predicting the end
 * state of a given ore sample. You have carefully studied the different structures that the ore can take and which
 * transitions it undergoes. It appears that, while random, the probability of each structure transforming is fixed.
 * That is, each time the ore is in 1 state, it has the same probabilities of entering the next state (which might be
 * the same state).  You have recorded the observed transitions in a matrix. The others in the lab have hypothesized
 * more exotic forms that the ore can become, but you haven't seen all of them.
 *
 * Write a function solution(m) that takes an array of array of nonnegative ints representing how many times that
 * state has gone to the next state and return an array of ints for each terminal state giving the exact
 * probabilities of each terminal state, represented as the numerator for each state, then the denominator for all of
 * them at the end and in simplest form. The matrix is at most 10 by 10. It is guaranteed that no matter which state
 * the ore is in, there is a path from that state to a terminal state. That is, the processing will always eventually
 * end in a stable state. The ore starts in state 0. The denominator will fit within a signed 32-bit integer during
 * the calculation, as long as the fraction is simplified regularly.
 *
 * For example, consider the matrix m:
 * [
 *   [0,1,0,0,0,1],  # s0, the initial state, goes to s1 and s5 with equal probability
 *   [4,0,0,3,2,0],  # s1 can become s0, s3, or s4, but with different probabilities
 *   [0,0,0,0,0,0],  # s2 is terminal, and unreachable (never observed in practice)
 *   [0,0,0,0,0,0],  # s3 is terminal
 *   [0,0,0,0,0,0],  # s4 is terminal
 *   [0,0,0,0,0,0],  # s5 is terminal
 * ]
 * So, we can consider different paths to terminal states, such as:
 * s0 -> s1 -> s3
 * s0 -> s1 -> s0 -> s1 -> s0 -> s1 -> s4
 * s0 -> s1 -> s0 -> s5
 * Tracing the probabilities of each, we find that
 * s2 has probability 0
 * s3 has probability 3/14
 * s4 has probability 1/7
 * s5 has probability 9/14
 * So, putting that together, and making a common denominator, gives an answer in the form of
 * [s2.numerator, s3.numerator, s4.numerator, s5.numerator, denominator] which is
 * [0, 3, 2, 9, 14].
 * @author tu.tonquang
 */
public class DoomsdayFuel {

    public static int[] solution(int[][] mat) {
        Transform transform = transform(mat);
        separateQR(transform);

        int[][] N = findInverseMat(transform.Q);
        return null;
    }

    public static Transform transform(int[][] mat) {
        int stableCount = 0;
        int[] sums = new int[mat.length];
        for (int i = 0; i < mat.length; i++) {
            sums[i] = sum(mat[i]);
            if (sums[i] == 0) {
                stableCount ++;
            }
        }

        int index = 0;
        Fraction[][] newMat = new Fraction[mat.length][mat.length];
        for (int i = 0; i < mat.length; i++) {
            if (sums[i] != 0) {
                for (int j = 0; j < mat.length; j++) {
                    newMat[index][j] = new Fraction(mat[i][j], sums[i]);
                }
                index ++;
            }
        }

        for (int i = 0; i < mat.length; i++) {
            if (sums[i] == 0) {
                for (int j = 0; j < mat.length; j++) {
                    newMat[index][j] = new Fraction(0, 1);
                }
                index ++;
            }
        }

        return new Transform(newMat, sums, stableCount);
    }

    public static void separateQR(Transform transform) {
        Fraction[][] Q = new Fraction[transform.transientLen][transform.transientLen];
        Fraction[][] R = new Fraction[transform.transientLen][transform.stableLen];

        Fraction[][] newMat = transform.newMat;
        for (int i = 0; i < transform.transientLen; i++) {
            for (int j = 0; j < transform.transientLen; j++) {
                if (i == j) {
                    Q[i][j] = Fraction.minus(1, newMat[i][j]);
                } else {
                    Q[i][j] = newMat[i][j].copy();
                }
            }
        }

        for (int i = 0; i < transform.transientLen; i++) {
            for (int j = 0; j < transform.stableLen; j++) {
                R[i][j] = newMat[i][j + transform.transientLen].copy();
            }
        }

        transform.Q = Q;
        transform.R = R;
    }

    public static int[][] findInverseMat(Fraction[][] Q) {
        Fraction[][] inverse = new Fraction[Q.length][Q.length * 2];
        for (int i = 0; i < Q.length; i++) {
            for (int j = 0; j < Q.length; j++) {
                inverse[i][j] = Q[i][j].copy();
                if (i == j) {
                    inverse[i][Q.length + j] = new Fraction(1, 1);
                } else {
                    inverse[i][Q.length + j] = new Fraction(0, 1);
                }
            }
        }


        for (int i = 0; i < inverse.length; i++) {
            for (int j = i + 1; j < inverse.length; j++) {


            }
        }
        return null;

    }

    public static int[][] multiplyMat(int[][] mat1, int[][] mat2) {
        return null;
    }

    public static int sum(int[] arr) {
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        return sum;
    }

    public static class Fraction {
        int numerator;
        int denominator;

        public Fraction(int numerator, int denominator) {
            this.numerator = numerator;
            this.denominator = denominator;
            reduce();
        }

        private void reduce() {
            int g = gcd(numerator, denominator);
            numerator = numerator / g;
            denominator = denominator / g;
        }

        private int gcd(int x, int y) {
            if (x % y == 0) {
                return y;
            }
            return gcd(y, x % y);
        }

        public static Fraction minus(int val, Fraction fraction) {
            return new Fraction(fraction.denominator - fraction.numerator, fraction.denominator);
        }

        public Fraction copy() {
            return new Fraction(numerator, denominator);
        }
    }

    public static class Transform {
        Fraction[][] newMat;
        int[] sums;
        int stableLen;
        int transientLen;
        int len;

        Fraction[][] Q;
        Fraction[][] R;

        public Transform(Fraction[][] newMat, int[] sums, int stableLen) {
            this.newMat = newMat;
            this.sums = sums;
            this.len = newMat.length;
            this.stableLen = stableLen;
            this.transientLen = len - stableLen;
        }
    }

}
