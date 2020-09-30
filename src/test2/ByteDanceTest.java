package test2;

import java.util.Arrays;

/**
 * // Suppose you have N integers from 1 to N. We define a beautiful arrangement as an array that is constructed by the
 * // se N numbers successfully if one of the following is true for the ith position (1 <= i <= N) in this array:
 * //1. The number at the ith position is divisible by i. (nums[i]% i == 0)
 * // 2. i is divisible by the number at the ith position. (i % nums[i] == 0)
 *
 * //Now given N, how many beautiful arrangements can you construct?
 * //2 -> 2
 * //[1,2],[2,1]
 *
 * // 3
 * [1, 2, 3]
 *
 */
public class ByteDanceTest {

    public static void main(String[] args) {
        //System.out.println(test(3));
        X1 x1 = new X1();
        X2 x2 = new X2();
        x1.s = "tuton";
        x2.s = x1.s;
        x2.s = "abc";
        System.out.println(x1.s);
        System.out.println(x2.s);
    }

    public static int test(int N) {
        Result res = new Result();
        int[] items = new int[N];
        boolean[] mark = new boolean[N + 1];
        generate(items, mark, 0, res);
        return res.count;
    }

    public static void generate(int[] items, boolean[] mark, int index, Result res) {
        if (index == items.length) {
            if (isBeautyArr(items)) {
                res.count ++;
            }
            return;
        }

        for (int i = 0; i < items.length; i++) {
            if (!mark[i]) {
                mark[i] = true;
                items[index] = i + 1;
                generate(items, mark, index + 1, res);
                mark[i] = false;

                // 1 -> i -> N , memo[i] is beauty = if (item[i] is beauty + memo[i + 1] is beauty)
            }
        }

    }

    public static boolean isBeautyArr(int[] items) {
        for (int i = 0; i < items.length; i++) {
            if (!(items[i] % (i + 1) == 0 || (i + 1) % items[i] == 0)) {
                return false;
            }
        }

        System.out.println(Arrays.toString(items));
        return true;
    }

    public static class Result {
        int count = 0;
    }

    public static class X1 {
        String s;
    }

    public static class X2 {
        String s;
    }


}
