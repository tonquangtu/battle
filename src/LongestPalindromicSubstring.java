import java.util.ArrayList;

public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        if (s.length() == 0) {
            return "";
        }
        int count = 1;
        int max = 1;
        int totalMiddle = 0;
        int totalMiddleMax = 0;

        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            int middle = totalMiddle / 2;
            int reflectIndex = totalMiddle % 2 == 0 ? middle - (i - middle) : middle - (i - middle - 1);

            if (reflectIndex >= 0 && c == s.charAt(reflectIndex)) {
                count += 2;
            } else if (count == 1 && c == s.charAt(i - 1)) {
                totalMiddle = i + i - 1;
                count = 2;
            } else {
                i = middle + 1;
                if (totalMiddle % 2 != 0 || s.charAt(middle) != s.charAt(middle + 1)) {
                    totalMiddle = (middle + 1) * 2;
                    count = 1;
                } else  {
                    totalMiddle = middle + middle + 1;
                    count = 2;
                }
            }

            if (count > max) {
                max = count;
                totalMiddleMax = totalMiddle;
            }
        }

        int begin, end;
        int middle = totalMiddleMax / 2;
        // max element is odd
        if (max % 2 != 0) {
            begin = middle - (max - 1) / 2;
            end = middle + (max - 1) / 2;
        } else {
            begin = middle - (max - 2) / 2;
            end = middle + 1 + (max - 2) / 2;
        }
        return s.substring(begin, end + 1);
    }
}

class TestLongestPalindromicSubstring {
    public static void main(String[] args) {
        var tester = new LongestPalindromicSubstring();

        var list = new ArrayList<String>();
//        list.add("babad"); // bab, aba
//        list.add("cbbd"); // bb
//        list.add("ababcdcbaff"); // abcdcba
//        list.add("ababcddcbaff"); // abcddcba
//        list.add("abab"); // aba
        list.add("cccccc"); // cccc
//        list.add("aabbccbbaaffgg");
//        list.add("aaaaa"); // aaaaaaa
//        list.add("aaabaaaa"); // aaabaaa

        for (int i = 0; i < list.size(); i++) {
            var res = tester.longestPalindrome(list.get(i));
            System.out.println(list.get(i) + "--> " + res);
        }
    }
}
