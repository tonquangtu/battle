/**
 * https://leetcode.com/problems/string-to-integer-atoi/
 */

import java.util.ArrayList;

public class StringToInteger_Atoi {

    public int myAtoi(String str) {
        if (str.length() == 0) {
            return 0;
        }

        int begin = 0;
        for (; begin < str.length(); begin++) {
            if (str.charAt(begin) != ' ') {
                break;
            }
        }
        if (begin == str.length()) {
            return 0;
        }
        char firstChar = str.charAt(begin);
        if (firstChar != '-' && firstChar != '+' && !Character.isDigit(firstChar)) {
            return 0;
        }
        int end = begin + 1;
        for (; end < str.length(); end ++) {
            if (!Character.isDigit(str.charAt(end))) {
                break;
            }
        }
        if (firstChar == '-' || firstChar == '+') {
            begin ++;
        }
        for (; begin < end; begin ++) {
            if (str.charAt(begin) != '0') {
                break;
            }
        }
        String value = str.substring(begin, end);
        if (value.length() == 0) {
            return 0;
        }
        // string too long
        if (value.length() > 10) {
            if (firstChar == '-') {
                return Integer.MIN_VALUE;
            }
            return Integer.MAX_VALUE;
        }
        if (firstChar == '-') {
            value = '-' + value;
        }
        long number = Long.parseLong(value);
        if (number > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        if (number < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        return (int)number;
    }
}

class TestAtoi {
    public static void main(String[] args) {
        var atoi = new StringToInteger_Atoi();
        var input = new ArrayList<String>();
        var expected = new ArrayList<Integer>();

        input.add("42");
        expected.add(42);

        input.add("   -42");
        expected.add(-42);

        input.add("4193 with words");
        expected.add(4193);

        input.add("words and 987");
        expected.add(0);

        input.add("-91283472332");
        expected.add(-2147483648);

        input.add("+");
        expected.add(0);

        input.add("2147483646");
        expected.add(2147483646);

        input.add("20000000000000000000");
        expected.add(Integer.MAX_VALUE);

        input.add("-0020000000000000000000");
        expected.add(Integer.MIN_VALUE);

        input.add("    0000000000000   ");
        expected.add(0);

        for (var i = 0; i < input.size(); i++) {
            var out = atoi.myAtoi(input.get(i));
            if (out != expected.get(i)) {
                System.out.println("Test failed at: " + i);
                System.out.println("Input: " + input.get(i));
                System.out.println("Expected: " + expected.get(i));
                System.out.println("Output: " + out);
            }
        }

    }
}
