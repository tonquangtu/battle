import java.util.ArrayList;
import java.util.HashMap;

/**
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 */
public class LongestSubstringWithoutRepeatingCharacters {
    // using hash map to marked
    public int lengthOfLongestSubstring(String s) {
        int count = 0;
        int max = 0;
        int begin = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            Integer index = map.get(c);
            if (index == null || index < begin) {
                count++;
            } else {
                begin = index;
                count = i - index;
            }
            if (count > max) {
                max = count;
            }
            map.put(c, i);
        }
        return max;
    }

    // using array to marked
    public int lengthOfLongestSubstring2(String s) {
        int count = 0;
        int max = 0;
        int begin = 0;
        int[] map = new int [256];
        for (int i = 0; i < 256; i++) { map[i] = -1; }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int index = map[(int)c];
            if (index < 0 || index < begin) {
                count++;
            } else {
                begin = index;
                count = i - index;
            }
            if (count > max) {
                max = count;
            }
            map[(int)c] = i;
        }
        return max;
    }
}

class TestLSWRP {
    public static void main(String[] args) {
        var tester = new LongestSubstringWithoutRepeatingCharacters();
        var list = new ArrayList<String>();
        list.add("abcabcbb"); // 3
        list.add("dvdf"); // 3
        list.add("abba"); // 2
        for (var s : list) {
            System.out.println(tester.lengthOfLongestSubstring2(s));
        }
    }
}
