import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubString {
    public static void main(String[] args) {
        var solution = new Solution();
        String s = "ADOBECODEBANC";
        String t = "ABC";
        var res = solution.minWindow(s, t);
        System.out.println(res);
    }
}
class Solution {
    public String minWindow(String s, String t) {
        int sl = s.length();
        int tl = t.length();
        if (sl == 0 || tl == 0) {
            return "";
        }

        Map<Character, Integer> map = mapping(t);
        int existCount = 0;
        int start = 0;
        int minStart = -1;
        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < sl; i++) {
            Character c = s.charAt(i);
            Integer charCount = map.get(c);
            if (charCount != null) {
                charCount = charCount - 1;
                map.put(c, charCount);
                if (charCount >= 0) {
                    existCount ++;
                    if (existCount == tl) {
                        while (start + tl - 1 <= i) {
                            char startChar = s.charAt(start);
                            Integer startCharCount = map.get(startChar);
                            if (startCharCount == null) {
                                start ++;
                            } else if (startCharCount < 0) {
                                start ++;
                                map.put(startChar, startCharCount + 1);
                            } else {
                                map.put(startChar, startCharCount + 1);
                                break;
                            }
                        }
                        if (i - start + 1 < minDistance) {
                            minDistance = i - start + 1;
                            minStart = start;
                        }
                        existCount --;
                        start ++;
                    }
                }
            }
        }

        return minStart == -1 ? "" : s.substring(minStart, minStart + minDistance);
    }

    public Map<Character, Integer> mapping(String t) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            Integer counter = map.get(t.charAt(i));
            if (counter == null) {
                map.put(t.charAt(i), 1);
            } else {
                map.put(t.charAt(i), counter + 1);
            }
        }
        return map;
    }

}