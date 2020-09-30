package competion.google.foobar;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

/**
 * You need to pass a message to the bunny prisoners, but to avoid detection, the code you agreed to use is...
 * obscure, to say the least. The bunnies are given food on standard-issue prison plates that are stamped with
 * the numbers 0-9 for easier sorting, and you need to combine sets of plates to create the numbers in the code.
 * The signal that a number is part of the code is that it is divisible by 3. You can do smaller numbers like 15
 * and 45 easily, but bigger numbers like 144 and 414 are a little trickier. Write a program to help yourself
 * quickly create large numbers for use in the code, given a limited number of plates to work with.
 *
 * You have L, a list containing some digits (0 to 9). Write a function solution(L) which finds the largest number
 * that can be made from some or all of these digits and is divisible by 3. If it is not possible to make such a
 * number, return 0 as the solution. L will contain anywhere from 1 to 9 digits.  The same digit may appear multiple
 * times in the list, but each element in the list may only be used once.
 * @author tu.tonquang
 */
public class PleasePassTheCodedMessage {
    public static void main(String[] args) {
        System.out.println(solution(new int[] {1}));
        System.out.println(solution(new int[] {2, 3, 4, 4}));
        System.out.println(solution(new int[] {9, 9, 5}));
        System.out.println(solution(new int[] {3, 1, 4, 1}));
        //System.out.println(solution(new int[] {3, 1, 4, 1, 5, 9}));
    }

    public static int solution(int[] l) {
        Arrays.sort(l);
        int [] nums = new int[l.length];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = l[nums.length - 1 - i];
        }

        Result  res = new Result();
        int[] placeholder = new int[nums.length];
        // boolean[] mark = new boolean[nums.length];
        findNum(nums, placeholder, 0, 0, res);
        return res.max;
    }

    public static void findNum(int[] nums, int[] placeholder, int index, int from, Result res) {
        if (index >= nums.length) {
            return;
        }
        for (int i = from; i < nums.length; i++) {
            placeholder[index] = nums[i];
            if (divisibleBy3(placeholder, index)) {
                res.setMax(getNum(placeholder, index));
            }
            findNum(nums, placeholder, index + 1, i + 1, res);
        }
    }

    public static boolean divisibleBy3(int[] placeholder, int index) {
        int sum = 0;
        for (int i = 0; i <= index; i++) {
            sum += placeholder[i];
        }
        return sum % 3 == 0;
    }

    public static int getNum(int[] placeholder, int index) {
        int num = 0;
        for (int i = 0; i <= index; i++) {
            num = num * 10 + placeholder[i];
        }
        return num;
    }

    public static class Result {
        int max = 0;
        public void setMax(int val) {
            max = Math.max(max, val);
        }
    }

}
