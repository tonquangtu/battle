import java.util.ArrayList;

/**
 * https://leetcode.com/problems/regular-expression-matching/
 * Use recursive algorithm to check string s is matching with partern p
 */
public class RegularExpressionMatching {

    public boolean isMatch(String s, String p) {
        int sLength = s.length();
        int pLength = p.length();
        if (pLength == 0) {
            return sLength == 0;
        }
        if (sLength == 0) {
            if (pLength == 1) {
                return false;
            } else {
                return p.charAt(1) == '*' && isMatch(s, p.substring(2));
            }
        }
        boolean firstMatch = (s.charAt(0) == p.charAt(0)) || p.charAt(0) == '.';
        if (pLength >= 2 && p.charAt(1) == '*') {
            return isMatch(s, p.substring(2)) || (firstMatch && isMatch(s.substring(1), p));
        } else if (pLength == 1) {
            return firstMatch && s.length() == 1;
        } else {
            return firstMatch && isMatch(s.substring(1), p.substring(1));
        }
    }

    public boolean isMatch2(String text, String pattern) {
        if (pattern.isEmpty()) return text.isEmpty();
        boolean first_match = (!text.isEmpty() &&
                (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));

        if (pattern.length() >= 2 && pattern.charAt(1) == '*'){
            return (isMatch(text, pattern.substring(2)) ||
                    (first_match && isMatch(text.substring(1), pattern)));
        } else {
            return first_match && isMatch(text.substring(1), pattern.substring(1));
        }
    }

    // use DP top-down: recursive with remember memory
    // Basically this logic is the same with recursive algorithm in above (isMatch, isMatch2)
    // It just need to have 2 dimension matrix to save the value that calculated in previous call
    // mark[i][j] to present s[0->i]  is matching with p[0->j]
    private int [][] mark;
    public boolean isMatch3(String s, String p) {
        this.mark = new int[s.length() + 1][p.length() + 1];
        for (int i = 0; i <= s.length(); i++) {
            for (int j = 0; j <= p.length(); j++) {
                mark[i][j] = -1;
            }
        }
        return doMatching(s, p, 0, 0);
    }

    public boolean doMatching(String s, String p, int si, int pi) {
        if (mark[si][pi] != -1) {
            return mark[si][pi] == 1;
        }
        if (pi == p.length()) {
            return si == s.length();
        }
        boolean firstMatch = (si < s.length() && (p.charAt(pi) == s.charAt(si) || p.charAt(pi) == '.'));
        boolean matching;
        if (pi + 1 < p.length() && p.charAt(pi + 1) == '*') {
            matching = doMatching(s, p, si, pi + 2) || (firstMatch && doMatching(s, p, si + 1, pi));
        } else {
            matching = firstMatch && doMatching(s, p, si + 1, pi + 1);
        }
        mark[si][pi] = matching ? 1 : 0;
        return matching;
    }
}

class TestREM {
    public static void main(String[] args) {
        var rem = new RegularExpressionMatching();
        var sl = new ArrayList<String>();
        var pl = new ArrayList<String>();

        // false
        var s = "mississippi";
        var p = "mis*is*p*.";
        sl.add(s);
        pl.add(p);

        // true
        s = "aab";
        p = "c*a*b";
        sl.add(s);
        pl.add(p);

        // true
        s = "ab";
        p = ".*";
        sl.add(s);
        pl.add(p);

        // false
        s = "ab";
        p = ".*c";
        sl.add(s);
        pl.add(p);

        // false
        s = "bbaa";
        p = "a...";
        sl.add(s);
        pl.add(p);

        // false
        s = "mississippi";
        p = "mis*is*ip*.";
        sl.add(s);
        pl.add(p);

        // true
        s = "";
        p = ".*";
        sl.add(s);
        pl.add(p);

        for (var i = 0; i < sl.size(); i++) {
            var matching = rem.isMatch3(sl.get(i), pl.get(i));
            System.out.println(matching);
        }
    }
}
