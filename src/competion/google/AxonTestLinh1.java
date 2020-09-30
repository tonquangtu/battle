package competion.google;

import java.util.Arrays;
import java.util.Stack;

public class AxonTestLinh1 {
    public static void main(String[] args) {
        //System.out.println(solution(""));
        //System.out.println(solution("><<><"));
        //System.out.println(solution(">><"));
        //System.out.println(solution("<>>"));
        //System.out.println(solution("<<>"));
        //
        //
        //System.out.println(solution(">>"));
        //System.out.println(solution(">"));
        //
        //
        ////<<<<<>>>>><<<>>>
        ////<<<<>>>>><<<>>
        //System.out.println(solution("<<>>>>><<<>>"));
        System.out.println(Arrays.toString(solution(new double[] {5.4, 3.3, 5.0})));

    }

    public static long[] solution(double[] prices) {
        Result res = new Result();
        double sum = 0.0;
        long[] items = new long[prices.length];
        for (int i = 0; i < prices.length; i++) {
            sum += prices[i];
        }
        res.sum = sum;
        doSolution(prices, items, 0, res);
        return res.items;
    }

    public static void doSolution(double[] prices, long[] items, int index, Result res) {
        if (index == prices.length) {
            double delta = Math.abs(res.sum - sum(items));
            if (delta < res.min) {
                res.min = delta;
                res.items = items;
            }
            return;
        }
        items[index] = (long)Math.floor(prices[index]);
        doSolution(prices, items, index + 1, res);
        items[index] = (long)Math.ceil(prices[index]);
        doSolution(prices, items, index + 1, res);
    }

    public static long sum(long[] items) {
        long sum = 0;
        for (long num : items) {
            sum += num;
        }
        return sum;
    }

    public static class Result {
        double sum;
        double min = Long.MAX_VALUE;
        long[] items;
    }





    //public static String solution(String angles) {
    //    int len = angles.length();
    //    if (len == 0) {
    //        return "";
    //    }
    //    StringBuilder builder = new StringBuilder();
    //    Stack<Character> stack = new Stack<>();
    //    for (int i = 0; i < len; i++) {
    //        char c = angles.charAt(i);
    //        if (c == '<') {
    //            stack.add(c);
    //        } else {
    //            if (!stack.isEmpty() && stack.peek() == '<') {
    //                stack.pop();
    //            } else {
    //                stack.add(c);
    //            }
    //        }
    //    }
    //
    //    int open = 0;
    //    int close = 0;
    //    while (!stack.isEmpty()) {
    //        if (stack.pop() == '<') {
    //            open ++;
    //        } else {
    //            close ++;
    //        }
    //    }
    //    addChar(builder, close, '<');
    //    builder.append(angles);
    //    addChar(builder, open, '>');
    //    return builder.toString();
    //}
    //
    //public static void addChar(StringBuilder builder, int times, char c) {
    //    for (int i = 0; i < times; i++) {
    //        builder.append(c);
    //    }
    //}
}
