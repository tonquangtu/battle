import java.util.ArrayList;

/***
 * Link to problem: https://leetcode.com/problems/longest-valid-parentheses/
 */
public class LongestValidParentheses {

    public static void main(String[] args) {
        String s = "";
        var list = new ArrayList<String>();
        s = "((()()))))()()()(())"; // 10
        list.add(s);
        s = ")()())"; // 4
        list.add(s);
        s = "(()";// 2
        list.add(s);
        s = "(()(((()"; // 2
        list.add(s);
        s = "()(()"; // 2
        list.add(s);
        s = "(()()"; // 4
        list.add(s);
        s = "(()())"; // 6
        list.add(s);
        for (var item : list) {
            int longestValid = new LongestValidParentheses().longestValidParentheses(item);
            System.out.println(longestValid);
        }
    }

    public int longestValidParentheses(String s) {
        int length = s.length();
        if (length == 0) {
            return 0;
        }

        int [] mark = new int [length];
        int [] stack = new int [length];
        int stackPos = -1;
        int openCharIndex = -1;
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                openCharIndex ++;
                stackPos ++;
                mark[openCharIndex] = 0;
                stack[stackPos] = openCharIndex;
            } else {
                if (stackPos >= 0) {
                    int openCharPos = stack[stackPos];
                    mark[openCharPos] = 1;
                    stackPos --;
                } else {
                    openCharIndex ++;
                    mark[openCharIndex] = 0;
                }
            }
        }

        // previous step to flat input string to this array
        // 1 1 0 1 1 1 1 0 1 --> longest valid: 4
        int currValid = 0;
        int longestValid = 0;
        for (int i = 0; i <= openCharIndex; i++) {
            if (mark[i] == 1) {
                currValid ++;
                if (currValid > longestValid) {
                    longestValid = currValid;
                }
            } else {
                currValid = 0;
            }
        }
        return longestValid * 2;
    }
}
